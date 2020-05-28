package com.bnp.bond_management.database.entity;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Parent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "bond_history")
@Data
public class BondHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Bond bond;
    @Column(name = "createdAt")
    private LocalDate createdAt;
    @Column(name = "updatedAt")
    private LocalDate updatedAt;
}