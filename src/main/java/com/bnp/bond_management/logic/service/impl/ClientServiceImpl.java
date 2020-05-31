package com.bnp.bond_management.logic.service.impl;

import com.bnp.bond_management.database.entity.Client;
import com.bnp.bond_management.database.repository.ClientRepository;
import com.bnp.bond_management.logic.model.request.CreateClientRequest;
import com.bnp.bond_management.logic.model.response.CreateClientResponse;
import com.bnp.bond_management.logic.service.ClientService;
import com.bnp.bond_management.web.exception.ClientAlreadyExistException;
import com.bnp.bond_management.web.exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public CreateClientResponse createNewClient(CreateClientRequest clientRequest) {
        CreateClientResponse response = new CreateClientResponse();
        Optional<com.bnp.bond_management.database.entity.Client> client = clientRepository.findById(clientRequest.getClient().getBornNumber());
        if (!client.isPresent()) {
            Client clientModel = clientRepository.save(clientRequest.getClient());
            response.setClient(clientModel);
        } else {
            throw new ClientAlreadyExistException(clientRequest.getClient().getBornNumber());
        }

        return response;
    }

    @Override
    public Client findClientById(String bornNumber) {
        return clientRepository.findById(bornNumber).orElseThrow(() -> new ClientNotFoundException(bornNumber));
    }
}
