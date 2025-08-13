package com.huynguyen.bbqrestaurantmanagement.enums.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorCodeDetail {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int code = 0;
    private String massage;
}
