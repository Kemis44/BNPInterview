package com.bnp.bond_management.database.repository;

import com.bnp.bond_management.database.entity.Bond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondRepository extends JpaRepository<Bond, Integer> {
}
