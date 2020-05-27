package com.bnp.bond_management.logic.service.impl;


import com.bnp.bond_management.database.repository.ClientRepository;
import com.bnp.bond_management.logic.mapper.LogicMapper;
import com.bnp.bond_management.logic.model.Bond;
import com.bnp.bond_management.logic.model.BondStatus;
import com.bnp.bond_management.database.repository.BondRepository;
import com.bnp.bond_management.logic.model.Client;
import com.bnp.bond_management.logic.model.request.BondApplyRequest;
import com.bnp.bond_management.logic.model.response.BondApplyResponse;
import com.bnp.bond_management.logic.service.BondService;
import com.bnp.bond_management.logic.service.BondValidationService;
import com.bnp.bond_management.web.exception.BondNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BondServiceImpl implements BondService {

    private final LogicMapper logicMapper;
    private final BondRepository repository;
    private final ClientRepository clientRepository;
    private final BondValidationService validationService;

    @PostConstruct
    public void saveDefaultBondsToDb() {
        List<com.bnp.bond_management.database.entity.Bond> defaultBonds = Arrays.asList(
                new com.bnp.bond_management.database.entity.Bond(1, 5, 0.10, 100000.00, BondStatus.FREE, new ArrayList<>(), null),
                new com.bnp.bond_management.database.entity.Bond(2, 6, 0.08, 50000.00, BondStatus.FREE, new ArrayList<>(), null),
                new com.bnp.bond_management.database.entity.Bond(3, 10, 0.04, 4000.00, BondStatus.FREE, new ArrayList<>(), null),
                new com.bnp.bond_management.database.entity.Bond(4, 8, 0.05, 900.00, BondStatus.FREE, new ArrayList<>(), null)
        );

        defaultBonds.forEach(bond -> repository.save(bond));
    }

    @Override
    public List<Bond> getAllBonds() {
        List<com.bnp.bond_management.database.entity.Bond> freeBonds = repository.findAll().stream()
                .filter(bond -> BondStatus.FREE.equals(bond.getStatus()))
                .collect(Collectors.toList());
        return logicMapper.mapToBondsModel(freeBonds);
    }

    @Override
    public Bond getBondById(int id) {
        Optional<com.bnp.bond_management.database.entity.Bond> foundBond = repository.findById(id);

        Bond bond;
        if (foundBond.isPresent()) {
            bond = logicMapper.mapToBondModel(foundBond.get());
        } else {
            throw new BondNotFoundException(id);
        }
        return bond;
    }

    @Override
    public BondApplyResponse applyBond(BondApplyRequest bondApplyRequest) {
        BondApplyResponse response = new BondApplyResponse();
        validationService.validateBondApplication(bondApplyRequest);

        List<Bond> bondsList = new ArrayList<>();
        Bond bond = logicMapper.mapToBondModel(repository.findById(bondApplyRequest.getBondId()).get());
        bond.setStatus(BondStatus.SOLD);
        bondsList.add(bond);

        Client client = Client.builder()
                .name(bondApplyRequest.getClient().getName())
                .surname(bondApplyRequest.getClient().getSurname())
                .bornNumber(bondApplyRequest.getClient().getBornNumber())
                .bonds(bondsList)
                .build();

        Client clientAfterSave = logicMapper.mapToClientModel(clientRepository.save(logicMapper.mapToClientEntity(client)));
        response.setClient(clientAfterSave);
        return response;
    }
}
