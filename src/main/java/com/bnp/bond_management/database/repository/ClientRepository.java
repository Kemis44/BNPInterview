package com.bnp.bond_management.database.repository;

import com.bnp.bond_management.database.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {

    Optional<Client> findClientByBornNumber(String bornNumber);
}
