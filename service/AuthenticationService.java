package com.huynguyen.bbqrestaurantmanagement.service;

import com.huynguyen.bbqrestaurantmanagement.dto.request.*;
import com.huynguyen.bbqrestaurantmanagement.dto.response.LoginResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.MyInfoResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.RefreshResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.RegisterResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;
import java.util.List;

public interface AuthenticationService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
    RefreshResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
    void logout(LogoutRequest request);
    MyInfoResponse myInfo();
    List<MyInfoResponse> getAll();
}
