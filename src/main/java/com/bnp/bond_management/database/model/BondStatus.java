package com.bnp.bond_management.database.model;

public enum BondStatus {

    FREE("FREE"),
    SOLD("SOLD");

    private String value;

    BondStatus(String value) {
        this.value = value;
    }

    public static BondStatus getStatusById(String value) {
        for (BondStatus status : BondStatus.values()) {
            if (status.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}
