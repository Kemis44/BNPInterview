package com.bnp.bond_management.web.controller;


import com.bnp.bond_management.logic.model.request.BondApplyRequest;
import com.bnp.bond_management.logic.model.request.CreateClientRequest;
import com.bnp.bond_management.logic.model.response.BondApplyResponse;
import com.bnp.bond_management.logic.model.response.CreateClientResponse;
import com.bnp.bond_management.logic.service.BondService;
import com.bnp.bond_management.logic.service.ClientService;
import com.bnp.bond_management.web.dto.v1.BondDTO;
import com.bnp.bond_management.web.dto.v1.ClientDTO;
import com.bnp.bond_management.web.dto.v1.request.BondApplyRequestDTO;
import com.bnp.bond_management.web.dto.v1.request.CreateClientRequestDTO;
import com.bnp.bond_management.web.dto.v1.response.BondApplyResponseDTO;
import com.bnp.bond_management.web.dto.v1.response.CreateClientResponseDTO;
import com.bnp.bond_management.web.mapper.ApiMapperV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BondController {

    @Autowired
    private ApiMapperV1 apiMapper;

    @Autowired
    private BondService bondService;

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/bonds", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BondDTO>> getAllBonds() {
        return ResponseEntity.ok(apiMapper.mapBondsToBondsDTOs(bondService.getAllBonds()));
    }

    @GetMapping(value = "/bonds/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BondDTO> getBondById(@PathVariable("id") @NotNull Integer id) {
        return ResponseEntity.ok(apiMapper.mapBondToBondDTO(bondService.getBondById(id)));
    }

    @PostMapping(value = "/bonds", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BondApplyResponseDTO> applyBond(@Valid @RequestBody BondApplyRequestDTO bondApplyRequestDTO) {
        BondApplyRequest bondApplyRequest = apiMapper.mapToBondApplyRequest(bondApplyRequestDTO);
        BondApplyResponse bondApplyResponse = bondService.applyBond(bondApplyRequest);
        BondApplyResponseDTO responseDTO = apiMapper.mapToBondApplyResponseDTO(bondApplyResponse);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping(value = "/clients", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateClientResponseDTO> createClient(@Valid @RequestBody CreateClientRequestDTO createClientRequestDTO) {
        CreateClientRequest createClientRequest = apiMapper.mapToCreateClientRequest(createClientRequestDTO);
        CreateClientResponse createClientResponse = clientService.createNewClient(createClientRequest);
        CreateClientResponseDTO createClientResponseDTO = apiMapper.mapToCreateClientResponseDTO(createClientResponse);
        return ResponseEntity.ok(createClientResponseDTO);
    }
}
