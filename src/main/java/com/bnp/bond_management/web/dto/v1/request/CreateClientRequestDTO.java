package com.bnp.bond_management.web.dto.v1.request;

import com.bnp.bond_management.web.dto.v1.ClientDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateClientRequestDTO {

    @Valid
    @NotNull
    @JsonProperty("client")
    private ClientDTO client;
}
