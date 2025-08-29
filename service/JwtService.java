package com.huynguyen.bbqrestaurantmanagement.service;

import com.huynguyen.bbqrestaurantmanagement.entity.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;

public interface JwtService {

    String generateToken(User user); // Tạo token cho một user khi đăng nhập thành công.

    boolean isTokenValid(String token, User user); // Kiểm tra xem token có hợp lệ hay không.

    String extractUserName(String token) throws ParseException; // Lấy username từ token (dùng để so sánh, hoặc lấy thông tin).

    SignedJWT verifyToken(String token) throws ParseException, JOSEException;

    String generateAccessToken(User user);
}
