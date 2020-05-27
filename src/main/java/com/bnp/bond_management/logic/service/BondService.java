package com.bnp.bond_management.logic.service;


import com.bnp.bond_management.logic.model.Bond;
import com.bnp.bond_management.logic.model.request.BondApplyRequest;
import com.bnp.bond_management.logic.model.response.BondApplyResponse;

import java.util.List;

public interface BondService {

    List<Bond> getAllBonds();

    Bond getBondById(int id);

    BondApplyResponse applyBond(BondApplyRequest bondApplyRequest);
}
