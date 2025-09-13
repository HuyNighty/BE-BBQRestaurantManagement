package com.huynguyen.bbqrestaurantmanagement.enums.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1000, "User existed.", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1001, "Role not found", HttpStatus.NOT_FOUND),
    EMAIL_EXISTED(1002, "Email existed", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_EXISTED(1003, "Phone number existed", HttpStatus.BAD_REQUEST),
    VALIDATION_FAILED(1007, "Validation failed: One or more fields are invalid", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1004, "User not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1005, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN(1006, "Invalid token", HttpStatus.BAD_REQUEST),
    LOGOUT_TOKEN(1007, "Token has been invalidated.", HttpStatus.BAD_REQUEST),
    ENTITY_NOT_FOUND(1008, "Entity not found", HttpStatus.NOT_FOUND),
    DUPLICATE_RESOURCE(1009, "Resource already exists", HttpStatus.BAD_REQUEST), UNAUTHORIZED(1010, "Unauthorized", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED(1011, "You do not have permission", HttpStatus.FORBIDDEN),
    ENTITY_EXISTED(1012, "Entity existed", HttpStatus.BAD_REQUEST),
    NO_RELATION_FOUND(1013, "Relation not found", HttpStatus.NOT_FOUND),
    ENUM_VALUE_INVALID(1014, "Enum value is invalid", HttpStatus.BAD_REQUEST),
    NAME_EXISTED(1015, "This name already exists", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST(1016, "Invalid request", HttpStatus.BAD_REQUEST),
    RATING_LIMIT(1017, "Rating must be in 1 - 10", HttpStatus.BAD_REQUEST),
    ;

    int code;
    String massage;
    HttpStatusCode httpStatusCode;
}
