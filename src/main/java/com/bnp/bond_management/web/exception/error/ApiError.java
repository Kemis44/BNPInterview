package com.bnp.bond_management.web.exception.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Šimek (nitramkemis44@gmail.com)
 */
@Data
public class ApiError {

    @JsonProperty("statusCode")
    private Integer statusCode;

    @JsonProperty("messageKey")
    private String messageKey;

    @JsonProperty("message")
    private String message;

    @JsonProperty("subErrors")
    private List<ApiSubError> apiSubErrors = new ArrayList<>();

    public ApiError(Integer statusCode, String messageKey, String message) {
        this.statusCode = statusCode;
        this.messageKey = messageKey;
        this.message = message;
    }

    public ApiError(String messageKey, String message) {
        this.messageKey = messageKey;
        this.message = message;
    }
}
