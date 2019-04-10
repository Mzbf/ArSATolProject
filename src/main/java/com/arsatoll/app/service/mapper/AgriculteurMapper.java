package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.AgriculteurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Agriculteur and its DTO AgriculteurDTO.
 */
@Mapper(componentModel = "spring", uses = {ZoneGeoMapper.class, UserMapper.class})
public interface AgriculteurMapper extends EntityMapper<AgriculteurDTO, Agriculteur> {

    @Mapping(source = "pays.id", target = "paysId")
    @Mapping(source = "user.id", target = "userId")
    AgriculteurDTO toDto(Agriculteur agriculteur);

    @Mapping(source = "paysId", target = "pays")
    @Mapping(source = "userId", target = "user")
    @Mapping(target = "agriculteurs", ignore = true)
    Agriculteur toEntity(AgriculteurDTO agriculteurDTO);

    default Agriculteur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Agriculteur agriculteur = new Agriculteur();
        agriculteur.setId(id);
        return agriculteur;
    }
}
