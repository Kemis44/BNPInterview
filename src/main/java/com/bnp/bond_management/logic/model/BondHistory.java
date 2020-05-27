package com.bnp.bond_management.logic.model;

import com.bnp.bond_management.database.entity.Bond;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BondHistory {

    private int id;
    private Bond bond;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
