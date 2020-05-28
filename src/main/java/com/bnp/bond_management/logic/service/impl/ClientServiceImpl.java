package com.bnp.bond_management.logic.service.impl;

import com.bnp.bond_management.database.repository.ClientRepository;
import com.bnp.bond_management.logic.mapper.LogicMapper;
import com.bnp.bond_management.logic.model.Client;
import com.bnp.bond_management.logic.model.request.CreateClientRequest;
import com.bnp.bond_management.logic.model.response.CreateClientResponse;
import com.bnp.bond_management.logic.service.ClientService;
import com.bnp.bond_management.web.exception.ClientAlreadyExistException;
import com.bnp.bond_management.web.exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LogicMapper logicMapper;

    @Override
    public CreateClientResponse createNewClient(CreateClientRequest clientRequest) {
        CreateClientResponse response = new CreateClientResponse();
        Client client;
        if (!clientRepository.findClientByBornNumber(clientRequest.getClient().getBornNumber()).isPresent()) {
            client = logicMapper.mapToClientModel(clientRepository.save(logicMapper.mapToClientEntity(clientRequest.getClient())));
            response.setClient(client);
        } else {
            throw new ClientAlreadyExistException(clientRequest.getClient().getBornNumber());
        }

        return response;
    }

    @Override
    public Client findClientById(String bornNumber) {
        return logicMapper.mapToClientModel(clientRepository.findClientByBornNumber(bornNumber).orElseThrow(() -> new ClientNotFoundException(bornNumber)));
    }
}
