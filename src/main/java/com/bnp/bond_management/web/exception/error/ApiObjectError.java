package com.bnp.bond_management.web.exception.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author Martin Šimek (nitramkemis44@gmail.com)
 */
@Data
@Builder
public class ApiObjectError extends ApiSubError {

    @JsonProperty("object")
    private String object;

    @JsonProperty("message")
    private String message;
}
