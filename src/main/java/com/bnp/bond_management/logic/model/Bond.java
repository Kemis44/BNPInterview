package com.bnp.bond_management.logic.model;

import com.bnp.bond_management.database.entity.BondHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bond {

    private int id;
    private int term;
    private double coupon;
    private double amount;
    private BondStatus status;
    private List<BondHistory> bondHistoryList;
}
