package com.bnp.bond_management.database.entity;


import com.bnp.bond_management.logic.model.BondStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "bond")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bond {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "term")
    private int term;
    @Column(name = "coupon")
    private double coupon;
    @Column(name = "amount")
    private double amount;
    @Column(name = "status")
    private BondStatus status;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bond", cascade = CascadeType.ALL)
    private List<BondHistory> bondHistoryList;
    @Column(name = "clientId")
    private String clientId;
}
