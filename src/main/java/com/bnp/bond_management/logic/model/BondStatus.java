package com.bnp.bond_management.logic.model;

import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

@Getter
public enum BondStatus {
    FREE(0),
    SOLD(1);

    private final Integer statusId;

    BondStatus(Integer statusId) {
        this.statusId = statusId;
    }

    public static Optional<BondStatus> getByStatusId(final Integer statusId) {
        return Stream.of(values())
                .filter(s -> s.getStatusId().equals(statusId))
                .findFirst();
    }

}
