package com.bnp.bond_management.web.dto.v1;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.stream.Stream;

@Getter
public enum BondStatusDTO {

    FREE(0),
    SOLD(1);

    private final Integer statusId;

    BondStatusDTO(Integer statusId) {
        this.statusId = statusId;
    }

    public static Optional<BondStatusDTO> getByStatusId(final Integer statusId) {
        return Stream.of(values())
                .filter(s -> s.getStatusId().equals(statusId))
                .findFirst();
    }
}
