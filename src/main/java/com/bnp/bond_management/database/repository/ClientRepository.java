package com.bnp.bond_management.database.repository;

import com.bnp.bond_management.database.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ClientRepository extends JpaRepository<Client, String> {
}
