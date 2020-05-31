package com.bnp.bond_management.logic.model.request;

import com.bnp.bond_management.database.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateClientRequest {
    private Client client;
}
