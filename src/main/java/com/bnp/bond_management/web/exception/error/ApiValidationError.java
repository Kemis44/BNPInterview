package com.bnp.bond_management.web.exception.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


/**
 * @author Martin Šimek (nitramkemis44@gmail.com)
 */
@Data
public class ApiValidationError extends ApiSubError {

    @JsonProperty("object")
    private String object;

    @JsonProperty("field")
    private String field;

    @JsonProperty("rejectedValue")
    private Object rejectedValue;

    @JsonProperty("message")
    private String message;

    public ApiValidationError() {
    }

    public ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
