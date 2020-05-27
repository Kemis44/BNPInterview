package com.bnp.bond_management.web.dto.v1.response;

import com.bnp.bond_management.web.dto.v1.ClientDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateClientResponseDTO {

    @JsonProperty("client")
    private ClientDTO client;
}
