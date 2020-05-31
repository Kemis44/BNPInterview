package com.bnp.bond_management.logic.model.response;

import com.bnp.bond_management.database.entity.Client;
import lombok.Data;

@Data
public class CreateClientResponse {
    private Client client;
}
