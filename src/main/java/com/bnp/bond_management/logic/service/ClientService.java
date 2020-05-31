package com.bnp.bond_management.logic.service;

import com.bnp.bond_management.database.entity.Client;
import com.bnp.bond_management.logic.model.request.CreateClientRequest;
import com.bnp.bond_management.logic.model.response.CreateClientResponse;

public interface ClientService {

    CreateClientResponse createNewClient(CreateClientRequest clientRequest);

    Client findClientById(String bornNumber);
}
