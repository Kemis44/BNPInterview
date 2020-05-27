package com.bnp.bond_management.web.mapper;


import com.bnp.bond_management.logic.model.Bond;
import com.bnp.bond_management.logic.model.request.BondApplyRequest;
import com.bnp.bond_management.logic.model.request.CreateClientRequest;
import com.bnp.bond_management.logic.model.response.BondApplyResponse;
import com.bnp.bond_management.logic.model.response.CreateClientResponse;
import com.bnp.bond_management.web.dto.v1.BondDTO;
import com.bnp.bond_management.web.dto.v1.request.BondApplyRequestDTO;
import com.bnp.bond_management.web.dto.v1.request.CreateClientRequestDTO;
import com.bnp.bond_management.web.dto.v1.response.BondApplyResponseDTO;
import com.bnp.bond_management.web.dto.v1.response.CreateClientResponseDTO;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface ApiMapperV1 {

    List<BondDTO> mapBondsToBondsDTOs(List<Bond> bonds);

    BondDTO mapBondToBondDTO(Bond bond);

    BondApplyResponseDTO mapToBondApplyResponseDTO(BondApplyResponse response);

    BondApplyRequest mapToBondApplyRequest(BondApplyRequestDTO requestDTO);

    CreateClientRequest mapToCreateClientRequest(CreateClientRequestDTO createClientRequestDTO);

    CreateClientResponseDTO mapToCreateClientResponseDTO(CreateClientResponse createClientResponse);

}
