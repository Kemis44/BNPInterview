package com.bnp.bond_management.web.exception;

public enum ExceptionsEnum {

    REQUEST_PARAMETERS_VALUES_NOT_VALID("bondService.requestParametersValuesNotValid"),
    REQUEST_PARAMETERS_FORMAT_NOT_VALID("bondService.requestParametersFormatNotValid"),
    BONDS_NOT_FOUND("bondService.bondsNotFound"),
    NOT_ALLOWED_THIS_TIME("bondService.applicationNotAllowedAtThisTime"),
    TOO_MORE_BONDS_PER_CLIENT("bondService.tooMoreBondsPerClient"),
    CLIENTS_NOT_FOUND("clientService.clientsNotFound"),
    CLIENT_ALREADY_EXIST("clientService.clientAlreadyExists"),
    INTERVIEW_API_ERROR("bondService.bondApiError");


    private String exceptionKey;


    ExceptionsEnum(String exceptionKey) {
        this.exceptionKey = exceptionKey;
    }

    public String getExceptionKey() {
        return exceptionKey;
    }
}
