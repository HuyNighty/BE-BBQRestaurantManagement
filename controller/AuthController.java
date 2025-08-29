package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.dto.request.LoginRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.request.LogoutRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.request.RefreshRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.request.RegisterRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.*;
import com.huynguyen.bbqrestaurantmanagement.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApiResponse<RegisterResponse> register(@RequestBody @Validated RegisterRequest request) {
        return ApiResponse.
                <RegisterResponse>builder()
                .result(authenticationService.register(request))
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return ApiResponse.
                <LoginResponse>builder()
                .result(authenticationService.login(request))
                .build();
    }

    @PostMapping("/refresh")
    public ApiResponse<RefreshResponse> refreshToken(@RequestBody @Valid RefreshRequest request) throws ParseException, JOSEException {
        return ApiResponse.
                <RefreshResponse>builder()
                .result(authenticationService.refreshToken(request))
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(@RequestBody @Valid LogoutRequest request) {
        authenticationService.logout(request);
        return ApiResponse.
                <String>builder()
                .result("Logout successful")
                .build();
    }

    @PostMapping("/myInfo")
    public ApiResponse<MyInfoResponse> myInfo() {
        return ApiResponse
                .<MyInfoResponse>builder()
                .result(authenticationService.myInfo())
                .build();
    }

    @GetMapping("/getAll")
    public ApiResponse<List<MyInfoResponse>> getAll() {
        return ApiResponse
                .<List<MyInfoResponse>>builder()
                .result(authenticationService.getAll())
                .build();
    }
}
