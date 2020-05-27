package com.bnp.bond_management.web.exception;

import lombok.Data;

@Data
public class ApiException extends RuntimeException {

    private String messageKey;

    public ApiException() {
    }

    public ApiException(String messageKey) {
        this.messageKey = messageKey;
    }

    public ApiException(String messageKey, String message) {
        super(message);
        this.messageKey = messageKey;
    }
}
