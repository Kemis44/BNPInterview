package com.bnp.bond_management.logic.service;

import com.bnp.bond_management.database.entity.Client;
import com.bnp.bond_management.database.repository.BondRepository;
import com.bnp.bond_management.database.repository.ClientRepository;
import com.bnp.bond_management.logic.model.BondStatus;
import com.bnp.bond_management.logic.model.request.BondApplyRequest;
import com.bnp.bond_management.web.exception.ApiException;
import com.bnp.bond_management.web.exception.BondApplicationNotAllowedException;
import com.bnp.bond_management.web.exception.ClientNotFoundException;
import com.bnp.bond_management.web.exception.ExceptionsEnum;
import com.bnp.bond_management.web.exception.TooMoreBondsPerClientException;
import com.bnp.bond_management.web.exception.error.ApiObjectError;
import com.bnp.bond_management.web.exception.error.ApiSubError;
import com.bnp.bond_management.web.exception.error.ApiValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BondValidationService {

    @Autowired
    private BondRepository bondRepository;

    @Autowired
    private ClientRepository clientRepository;

    private final int LATER_PM_HOUR = 22;
    private final int SOONER_AM_HOUR = 6;
    private final double MINIMAL_MIDNIGHT_AMOUNT = 1000.00;
    private final int MAX_NUMBER_OF_BONDS = 5;

    public void validateBondApplication(BondApplyRequest request) {
        checkBondFreeStatus(request);
        checkTimeOfApplication(request, LocalDateTime.now());
        checkMaxNumberOfBondsPerClient(request);
    }

    private void checkBondFreeStatus(BondApplyRequest request) {
        if (BondStatus.SOLD.equals(bondRepository.findById(request.getBondId()).get().getStatus())) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Bond with id: " + request.getBondId() + " is not FREE to sold.");
        }
    }

    private void checkMaxNumberOfBondsPerClient(BondApplyRequest request) {
        Optional<Client> client =  clientRepository.findById(request.getClient().getBornNumber());
        if (client.isPresent()) {
                int numberOfBondsForThisClient = client.get().getBonds().size();
                if (numberOfBondsForThisClient > MAX_NUMBER_OF_BONDS) {
                    throw new TooMoreBondsPerClientException(client.get().getBornNumber());
                }
        } else {
            throw new ClientNotFoundException(request.getClient().getName(), request.getClient().getSurname(), request.getClient().getBornNumber());
        }

    }

    private void checkTimeOfApplication(BondApplyRequest request, LocalDateTime now) {
        double bondAmount = bondRepository.findById(request.getBondId()).get().getAmount();
        if ((now.getHour() > LATER_PM_HOUR || now.getHour() < SOONER_AM_HOUR) && bondAmount > MINIMAL_MIDNIGHT_AMOUNT){
            throw new BondApplicationNotAllowedException("Bad time for application");
        }
    }


}
