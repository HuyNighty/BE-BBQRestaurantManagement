package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.entity.User;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.repository.InvalidatedTokenRepository;
import com.huynguyen.bbqrestaurantmanagement.service.JwtService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtServiceImpl implements JwtService {

    @NonFinal
    @Value("${jwt.signerKey}")
    private String signerKey;

    @NonFinal
    @Value("${jwt.validDuration}")
    private long validDuration;

    @NonFinal
    @Value("${jwt.refreshableDuration}")
    private long refreshableDuration;

    final InvalidatedTokenRepository invalidatedTokenRepository;

    @Override
    public String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet
                .Builder()
                .subject(user.getUserName())
                .issuer("BBQRestaurant")
                .issueTime(new Date())
                .expirationTime(Date.from(Instant.now().plus(validDuration, ChronoUnit.SECONDS)))
                .claim("scope", buildScope(user))
                .jwtID(UUID.randomUUID().toString())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Failed to sign JWT", e);
            throw new RuntimeException("Unable to sign key");
        }
    }

    private String buildScope(User user) {
        StringJoiner joiner = new StringJoiner(" ");
        if (user.getRoles() != null) {
            user.getRoles().forEach(role -> {
                joiner.add("ROLE_" + role.getRoleName());

                if (role.getRolePermissions() != null) {
                    role.getRolePermissions().forEach(permission ->
                        joiner.add(permission.getPermission().getPermissionName())
                    );
                }
            });
        }
        return joiner.toString();
    }

    @Override
    public boolean isTokenValid(String token, User user) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(signerKey.getBytes());

            boolean isVerified = signedJWT.verify(verifier);
            String userName = signedJWT.getJWTClaimsSet().getSubject();
            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            return isVerified && userName.equals(user.getUserName()) && expirationTime.after(new Date());
        } catch (ParseException | JOSEException e) {
            log.warn("Token invalid: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public String extractUserName(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (ParseException e) {
            log.warn("Cannot extract username from token");
            throw new RuntimeException("INVALID_TOKEN");
        }
    }

    @Override
    public SignedJWT verifyToken(String token) throws ParseException, JOSEException {
        // Xác thực token.

        // Giải mã token vừa mới được nhập vào, lấy "chữ ký" để xác thực
        SignedJWT signedJWT = SignedJWT.parse(token);

        // Chữ ký thật sự
        JWSVerifier verifier = new MACVerifier(signerKey);

        // So sánh hai chữ ký với nhau.
        boolean verified = signedJWT.verify(verifier);

        // Nếu không trùng thì quăng ra lỗi.
        if (!verified)
            throw new AppException(ErrorCode.INVALID_TOKEN);

        // Lấy thời gian bắt đầu của token.
        Date issueTime = signedJWT.getJWTClaimsSet().getIssueTime();

        // Check blacklist.
        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.LOGOUT_TOKEN);

        // Làm mới thời gian.
        Instant refreshExpiry = issueTime.toInstant().plus(refreshableDuration, ChronoUnit.SECONDS);

        // Nếu token quá hạn để refresh -> quăng ra lỗi.
        if (refreshExpiry.isBefore(Instant.now()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    @Override
    public String generateAccessToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet
                .Builder()
                .subject(user.getUserName())
                .issuer("BBQRestaurant")
                .issueTime(new Date())
                .expirationTime(Date.from(Instant.now().plus(validDuration, ChronoUnit.SECONDS)))
                .claim("scope", buildScope(user))
                .jwtID(UUID.randomUUID().toString())
                .build();

        Payload payload = jwtClaimsSet.toPayload();
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException("Failed to sign token", e);
        }
    }
}
