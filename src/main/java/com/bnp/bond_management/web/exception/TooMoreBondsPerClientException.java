package com.bnp.bond_management.web.exception;

public class TooMoreBondsPerClientException extends ApiException {

    public TooMoreBondsPerClientException() {
        super();
    }

    public TooMoreBondsPerClientException(String message) {
        super(ExceptionsEnum.TOO_MORE_BONDS_PER_CLIENT.getExceptionKey(), "No more bonds are allowed. Client with born number: " + message + " has reached 5 allowed bonds.");
    }
}
