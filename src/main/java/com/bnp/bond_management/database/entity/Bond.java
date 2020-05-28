package com.bnp.bond_management.database.entity;


import com.bnp.bond_management.logic.model.BondStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bond")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bond {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "term")
    private int term;
    @Column(name = "coupon")
    private double coupon;
    @Column(name = "amount")
    private double amount;
    @Column(name = "status")
    private BondStatus status;
    @OneToMany(mappedBy = "bond", cascade = CascadeType.ALL, orphanRemoval = true)
    private  List<BondHistory> bondHistoryList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_bornNumber")
    private Client client;

    public void add(BondHistory bondHistory) {
        if(bondHistoryList == null) {
            bondHistoryList = new ArrayList<>();
        }
        bondHistoryList.add(bondHistory);
        bondHistory.setBond(this);
    }
}
