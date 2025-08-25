package com.huynguyen.bbqrestaurantmanagement.exception;

import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCodeDetail;
import lombok.Getter;

import java.util.List;

@Getter
public class AppException extends RuntimeException {
    private ErrorCode errorCode;
    private List<ErrorCodeDetail> errors;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMassage());
        this.errorCode = errorCode;
    }

    public AppException(ErrorCode errorCode, List<ErrorCodeDetail> errors) {
        super(errorCode.getMassage());
        this.errorCode = errorCode;
        this.errors = errors;
    }
}
