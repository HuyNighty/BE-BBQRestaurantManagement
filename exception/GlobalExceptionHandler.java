package com.huynguyen.bbqrestaurantmanagement.exception;

import com.huynguyen.bbqrestaurantmanagement.dto.response.ApiResponse;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCodeDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<?>> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        ApiResponse<?> apiResponse = new ApiResponse<>();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMassage());

        // Nếu AppException có danh sách lỗi chi tiết, hãy thêm vào response
        // Điều này sẽ xuất hiện khi bạn ném new AppException(ErrorCode.VALIDATION_FAILED, validationErrors)
        if (exception.getErrors() != null && !exception.getErrors().isEmpty()) {
            apiResponse.setErrors(exception.getErrors());
        }

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse<String>> handlingJwtException(JwtException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.
                <String>builder()
                .code(ErrorCode.INVALID_TOKEN.getCode())
                .message(ErrorCode.INVALID_TOKEN.getMassage())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ErrorCodeDetail> details = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> ErrorCodeDetail
                        .builder()
                        .massage(fieldError.getDefaultMessage())
                        .build())
                .toList();

        ApiResponse<?> response = ApiResponse
                .builder()
                .code(ErrorCode.VALIDATION_FAILED.getCode())
                .message(ErrorCode.VALIDATION_FAILED.getMassage())
                .errors(details)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handlingAccessDeniedException(AccessDeniedException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiResponse
                        .builder()
                        .code(ErrorCode.ACCESS_DENIED.getCode())
                        .message(ErrorCode.ACCESS_DENIED.getMassage())
                        .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handlingHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse
                        .builder()
                        .code(ErrorCode.ENUM_VALUE_INVALID.getCode())
                        .message(ErrorCode.ENUM_VALUE_INVALID.getMassage())
                        .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<?>> handlingDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse
                        .builder()
                        .code(ErrorCode.NAME_EXISTED.getCode())
                        .message(ErrorCode.NAME_EXISTED.getMassage())
                        .build());
    }

}
