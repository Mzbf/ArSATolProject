package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.TypeDegatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TypeDegat and its DTO TypeDegatDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeDegatMapper extends EntityMapper<TypeDegatDTO, TypeDegat> {


    @Mapping(target = "degats", ignore = true)
    TypeDegat toEntity(TypeDegatDTO typeDegatDTO);

    default TypeDegat fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeDegat typeDegat = new TypeDegat();
        typeDegat.setId(id);
        return typeDegat;
    }
}
