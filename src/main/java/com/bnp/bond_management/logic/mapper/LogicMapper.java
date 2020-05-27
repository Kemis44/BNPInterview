package com.bnp.bond_management.logic.mapper;

import com.bnp.bond_management.database.entity.Bond;
import com.bnp.bond_management.database.entity.Client;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface LogicMapper {

    List<Bond> mapToBondsEntity(List<com.bnp.bond_management.logic.model.Bond> bonds);

    List<com.bnp.bond_management.logic.model.Bond> mapToBondsModel(List<Bond> bonds);

    Bond mapToBondEntity(com.bnp.bond_management.logic.model.Bond bond);

    com.bnp.bond_management.logic.model.Bond mapToBondModel(Bond bond);

    Client mapToClientEntity(com.bnp.bond_management.logic.model.Client client);

    com.bnp.bond_management.logic.model.Client mapToClientModel(Client client);
}
