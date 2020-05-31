package com.bnp.bond_management.logic.model.response;


import com.bnp.bond_management.database.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BondAdjustResponse {

    private Client client;
}
