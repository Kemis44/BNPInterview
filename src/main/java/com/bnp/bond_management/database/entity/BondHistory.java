package com.bnp.bond_management.database.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "bond_history")
@Getter
public class BondHistory {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bondId")
    private Bond bond;
    @Column(name = "createdAt")
    private LocalDate createdAt;
    @Column(name = "updatedAt")
    private LocalDate updatedAt;
}