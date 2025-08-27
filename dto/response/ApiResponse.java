package com.huynguyen.bbqrestaurantmanagement.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCodeDetail;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    @Builder.Default
    int code = 1000;

    String message;
    T result;
    private List<ErrorCodeDetail> errors;
}
