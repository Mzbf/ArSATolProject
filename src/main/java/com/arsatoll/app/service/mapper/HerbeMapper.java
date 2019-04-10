package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.HerbeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Herbe and its DTO HerbeDTO.
 */
@Mapper(componentModel = "spring", uses = {MethodeLutteMapper.class})
public interface HerbeMapper extends EntityMapper<HerbeDTO, Herbe> {

    @Mapping(source = "herbeML.id", target = "herbeMLId")
    HerbeDTO toDto(Herbe herbe);

    @Mapping(source = "herbeMLId", target = "herbeML")
    @Mapping(target = "herbes", ignore = true)
    @Mapping(target = "cultures", ignore = true)
    Herbe toEntity(HerbeDTO herbeDTO);

    default Herbe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Herbe herbe = new Herbe();
        herbe.setId(id);
        return herbe;
    }
}
