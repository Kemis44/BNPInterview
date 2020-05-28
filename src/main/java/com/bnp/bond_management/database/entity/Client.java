package com.bnp.bond_management.database.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Data
public class Client {

    @Id
    @Column(name = "bornNumber")
    private String bornNumber;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Bond.class, mappedBy = "client")
    private List<Bond> bonds;

    public void add(Bond bond) {
        if(bonds == null) {
            bonds = new ArrayList<>();
        }
        bonds.add(bond);
        bond.setClient(this);
    }

}
