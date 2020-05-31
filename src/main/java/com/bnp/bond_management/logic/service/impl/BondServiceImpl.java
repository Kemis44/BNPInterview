package com.bnp.bond_management.logic.service.impl;


import com.bnp.bond_management.database.entity.Bond;
import com.bnp.bond_management.database.entity.BondHistory;
import com.bnp.bond_management.database.entity.Client;
import com.bnp.bond_management.database.model.BondStatus;
import com.bnp.bond_management.database.repository.BondRepository;
import com.bnp.bond_management.database.repository.ClientRepository;
import com.bnp.bond_management.logic.model.request.BondAdjustRequest;
import com.bnp.bond_management.logic.model.request.BondApplyRequest;
import com.bnp.bond_management.logic.model.response.BondAdjustResponse;
import com.bnp.bond_management.logic.model.response.BondApplyResponse;
import com.bnp.bond_management.logic.service.BondService;
import com.bnp.bond_management.logic.service.BondValidationService;
import com.bnp.bond_management.web.exception.ApiException;
import com.bnp.bond_management.web.exception.BondNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BondServiceImpl implements BondService {

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
        List<Bond> freeBonds = repository.findAll().stream()
                .filter(bond -> BondStatus.FREE.equals(bond.getStatus()))
                .collect(Collectors.toList());
        return freeBonds;
    }

    @Override
    public Bond getBondById(int id) {
        Optional<com.bnp.bond_management.database.entity.Bond> foundBond = repository.findById(id);

        Bond bond;
        if (foundBond.isPresent()) {
            bond = foundBond.get();
        } else {
            throw new BondNotFoundException(id);
        }
        return bond;
    }

    @Override
    public BondApplyResponse applyBond(BondApplyRequest bondApplyRequest) {
        BondApplyResponse response = new BondApplyResponse();
        validationService.validateBondApplication(bondApplyRequest);

        Bond bond = repository.findById(bondApplyRequest.getBondId()).get();
        bond.setStatus(BondStatus.SOLD);

        BondHistory bondHistory = createFirstBondHistory(bond);
        List<BondHistory> bondHistoryList = new ArrayList<>();
        bondHistoryList.add(bondHistory);
        bond.setBondHistoryList(bondHistoryList);
        bondHistory.setBond(bond);

        Client client = clientRepository.findById(bondApplyRequest.getClient().getBornNumber()).get();
        client.getBonds().add(bond);
        bond.setClient(client);

        Client clientAfterSave = clientRepository.save(client);
        response.setClient(clientAfterSave);
        return response;
    }

    @Override
    public BondAdjustResponse adjustBond(BondAdjustRequest bondAdjustRequest) {
        BondAdjustResponse bondAdjustResponse = new BondAdjustResponse();
        Client client = clientRepository.findById(bondAdjustRequest.getClient().getBornNumber()).get();
        Bond bond = repository.findById(bondAdjustRequest.getBondId()).get();
        if (bond.getTerm() < bondAdjustRequest.getTerm()) {
            bond.setTerm(bondAdjustRequest.getTerm());
            bond.setCoupon(bond.getCoupon() * 0.9);

            BondHistory newHistoryAfterAdjust = createBondHistoryAfterAdjustment(bond);
            List<BondHistory> historyList = bond.getBondHistoryList();
            historyList.add(newHistoryAfterAdjust);
            newHistoryAfterAdjust.setBond(bond);


            Client clientSavedAfterAdjust = clientRepository.save(client);

            bondAdjustResponse.setClient(clientSavedAfterAdjust);
            return bondAdjustResponse;
        } else {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "New requested term: " + bondAdjustRequest.getTerm() + " is shorter then original: " + bond.getTerm() + ", this adjustment is not allowed!");
        }
    }

    private BondHistory createFirstBondHistory(Bond bond) {
        BondHistory bondHistory = BondHistory.builder()
                .amount(bond.getAmount())
                .coupon(bond.getCoupon())
                .term(bond.getTerm())
                .createdAt(LocalDate.now())
                .build();
        return bondHistory;
    }

    private BondHistory createBondHistoryAfterAdjustment(Bond bond) {
        BondHistory bondHistory = BondHistory.builder()
                .amount(bond.getAmount())
                .coupon(bond.getCoupon())
                .term(bond.getTerm())
                .updatedAt(LocalDate.now())
                .build();
        return bondHistory;
    }
}
