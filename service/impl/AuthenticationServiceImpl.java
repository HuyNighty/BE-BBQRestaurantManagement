package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.dto.request.*;
import com.huynguyen.bbqrestaurantmanagement.dto.response.LoginResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.MyInfoResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.RefreshResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.RegisterResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Customer;
import com.huynguyen.bbqrestaurantmanagement.entity.InvalidatedToken;
import com.huynguyen.bbqrestaurantmanagement.entity.Role;
import com.huynguyen.bbqrestaurantmanagement.entity.User;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCodeDetail;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.mapper.MyInfoMapper;
import com.huynguyen.bbqrestaurantmanagement.dto.response.MyInfoSource;
import com.huynguyen.bbqrestaurantmanagement.mapper.RegisterMapper;
import com.huynguyen.bbqrestaurantmanagement.repository.*;
import com.huynguyen.bbqrestaurantmanagement.service.AuthenticationService;
import com.huynguyen.bbqrestaurantmanagement.service.JwtService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    UserRepository userRepository;
    CustomerRepository customerRepository;
    RoleRepository roleRepository;
    MyInfoRepository myInfoRepository;

    MyInfoMapper myInfoMapper;
    RegisterMapper registerMapper;

    JwtService jwtService;
    InvalidatedTokenRepository invalidatedTokenRepository;

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        log.info("error");
        List<ErrorCodeDetail> validationErrors = validateRegistrationRequest(request);
        if (!validationErrors.isEmpty()) {
            throw new AppException(ErrorCode.VALIDATION_FAILED, validationErrors);
        }

        //2. Map RegisterRequest sang User, Customer.
        User user = registerMapper.toUser(request);
        Customer customer = registerMapper.toCustomer(request);

        //3. Mã hóa password.
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        //4. Gán Role User (Role mặc định của người đăng ký).
        Role roles = roleRepository.findById("USER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        user.setRoles(Set.of(roles));

        //5. Lưu User.
        user = userRepository.save(user);

        //6. Gán userId cho customerId.
        customer.setCustomerId(user.getUserId());
        customer.setUser(user);
        user.setCustomer(customer);

        //7. Lưu customer
        customer = customerRepository.save(customer);

        //8. Trả về response.
        return registerMapper.toRegisterResponse(user, customer);
    }

    private List<ErrorCodeDetail> validateRegistrationRequest(RegisterRequest request) {
        List<ErrorCodeDetail> errors = new ArrayList<>();

        if (userRepository.existsByUserName(request.getUserName())) {
            errors.add(buildErrorCodeDetail(ErrorCode.USER_EXISTED));
        }

        if (customerRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            errors.add(buildErrorCodeDetail(ErrorCode.PHONE_NUMBER_EXISTED));
        }

        if (customerRepository.existsByEmail(request.getEmail())) {
            errors.add(buildErrorCodeDetail(ErrorCode.EMAIL_EXISTED));
        }

        return errors;
    }

    private ErrorCodeDetail buildErrorCodeDetail(ErrorCode errorCode) {
        return ErrorCodeDetail
                .builder()
                .code(errorCode.getCode())
                .massage(errorCode.getMassage())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        // 1. Tìm user theo userName.
        User user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // 2. Kiểm tra password.
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        // 3. Tạo token.
        String token = jwtService.generateToken(user);

        // 4. Tạo response.
        return LoginResponse
                .builder()
                .authenticated(true)
                .userName(user.getUserName())
                .accessToken(token)
                .roles(user.getRoles()
                        .stream()
                        .map(Role::getRoleName)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public RefreshResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = jwtService.verifyToken(request.getRefreshToken());

        String jti = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jti)
                .expiryTime(expiryTime)
                .build();

        invalidatedTokenRepository.save(invalidatedToken);

        String userName = signedJWT.getJWTClaimsSet().getSubject();
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        String newAccessToken = jwtService.generateAccessToken(user);

        return RefreshResponse
                .builder()
                .success(true)
                .accessToken(newAccessToken)
                .build();
    }

    @Override
    public void logout(LogoutRequest request) {
        try {
            SignedJWT signedJWT = jwtService.verifyToken(request.getAccessToken());

            String jti = signedJWT.getJWTClaimsSet().getJWTID();
            Date expiry = signedJWT.getJWTClaimsSet().getExpirationTime();

            // Nếu jti không tồn tại, có nghĩa là trong db invalidedToken chưa có jti thì ta sẽ phải save nó lại. Lý do: Khi ta đã logout thì có nghĩa là token đó đã không còn có thể sử dụng được nữa, nên ta phải đưa nó vào cột invalidedToken.
            if (!invalidatedTokenRepository.existsById(jti)) {
                invalidatedTokenRepository.save(
                        InvalidatedToken.builder()
                                .id(jti)
                                .expiryTime(expiry)
                                .build()
                );
            }

            // Còn nếu token đã có trong db invalidedToken thì có nghĩa là token đó đã hết hạn không thể logout được nữa -> invalid_token.
        } catch (ParseException | JOSEException e) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
    }

    @Override
    public MyInfoResponse myInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        String userName = authentication.getName();

        User user = userRepository.findByUserName(userName).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_FOUND));


        Customer customer = customerRepository.findById(user.getUserId()).orElseThrow(() ->
                new AppException(ErrorCode.ENTITY_NOT_FOUND));

        MyInfoSource myInfoSource = new MyInfoSource(user, customer);

        return myInfoMapper.toMyInfoResponse(myInfoSource);
    }

    @Override
    public List<MyInfoResponse> getAll() {
        List<MyInfoSource> sources = myInfoRepository.getAllInfo();
        return myInfoMapper.tMyInfoResponseList(sources);
    }
}

