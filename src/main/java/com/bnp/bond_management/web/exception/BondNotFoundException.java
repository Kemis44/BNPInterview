package com.bnp.bond_management.web.exception;

public class BondNotFoundException extends ApiException {

    public BondNotFoundException() {
        super();
    }

    public BondNotFoundException(int id) {
        super(ExceptionsEnum.BONDS_NOT_FOUND.getExceptionKey(), "No bond found for Bond id: " + id);
    }
}
