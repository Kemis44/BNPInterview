package com.bnp.bond_management.web.exception;

public class ClientNotFoundException extends ApiException {

    public ClientNotFoundException() {
        super();
    }

    public ClientNotFoundException(String name, String surname, String bornNumber) {
        super(ExceptionsEnum.CLIENTS_NOT_FOUND.getExceptionKey(), "Client " + name + " " + surname + " with born number: " + bornNumber + ", not found. Please register new client.");
    }
}
