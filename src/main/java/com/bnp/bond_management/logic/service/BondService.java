package com.bnp.bond_management.logic.service;


import com.bnp.bond_management.database.entity.Bond;
import com.bnp.bond_management.logic.model.request.BondAdjustRequest;
import com.bnp.bond_management.logic.model.request.BondApplyRequest;
import com.bnp.bond_management.logic.model.response.BondAdjustResponse;
import com.bnp.bond_management.logic.model.response.BondApplyResponse;

import java.util.List;

public interface BondService {

    List<Bond> getAllBonds();

    Bond getBondById(int id);

    BondApplyResponse applyBond(BondApplyRequest bondApplyRequest);

    BondAdjustResponse adjustBond(BondAdjustRequest bondAdjustRequest);
}
