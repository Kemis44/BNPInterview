package com.bnp.bond_management.database.entity;


import com.bnp.bond_management.database.model.BondStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
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
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bond")
    private  List<BondHistory> bondHistoryList;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private Client client;
}
