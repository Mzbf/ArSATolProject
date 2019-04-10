package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.OrdreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ordre and its DTO OrdreDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrdreMapper extends EntityMapper<OrdreDTO, Ordre> {


    @Mapping(target = "ordres", ignore = true)
    Ordre toEntity(OrdreDTO ordreDTO);

    default Ordre fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ordre ordre = new Ordre();
        ordre.setId(id);
        return ordre;
    }
}
