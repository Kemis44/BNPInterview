package com.bnp.bond_management.logic.model.response;


import com.bnp.bond_management.logic.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BondApplyResponse {

    private Client client;
}
