package com.bnp.bond_management.web.exception;

public class BondApplicationNotAllowedException extends ApiException {

    public BondApplicationNotAllowedException() {
        super();
    }

    public BondApplicationNotAllowedException(String message) {
        super(ExceptionsEnum.NOT_ALLOWED_THIS_TIME.getExceptionKey(), "Application is not allowed in this time due to prevention of potentially illegal operations. Application can be applied only between 10:00 PM and 06:00 AM");
    }
}
