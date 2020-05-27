package com.bnp.bond_management.web.dto.v1.request;

import com.bnp.bond_management.web.dto.v1.ClientDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class BondApplyRequestDTO {

    @Valid
    @NotNull
    @JsonProperty("client")
    private ClientDTO client;

    @NotNull
    @JsonProperty("bondId")
    private Integer bondId;


}
