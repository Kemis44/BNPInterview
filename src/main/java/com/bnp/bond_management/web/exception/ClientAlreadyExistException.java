package com.bnp.bond_management.web.exception;

public class ClientAlreadyExistException extends ApiException {

    public ClientAlreadyExistException() {
        super();
    }

    public ClientAlreadyExistException(String bornNumber) {
        super(ExceptionsEnum.CLIENT_ALREADY_EXIST.getExceptionKey(), "Client with born number: " + bornNumber + " already exists, please sign in or register new client");
    }
}
