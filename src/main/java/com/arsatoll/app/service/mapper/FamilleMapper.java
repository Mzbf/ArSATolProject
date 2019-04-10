package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.FamilleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Famille and its DTO FamilleDTO.
 */
@Mapper(componentModel = "spring", uses = {OrdreMapper.class})
public interface FamilleMapper extends EntityMapper<FamilleDTO, Famille> {

    @Mapping(source = "ordre.id", target = "ordreId")
    FamilleDTO toDto(Famille famille);

    @Mapping(source = "ordreId", target = "ordre")
    Famille toEntity(FamilleDTO familleDTO);

    default Famille fromId(Long id) {
        if (id == null) {
            return null;
        }
        Famille famille = new Famille();
        famille.setId(id);
        return famille;
    }
}
