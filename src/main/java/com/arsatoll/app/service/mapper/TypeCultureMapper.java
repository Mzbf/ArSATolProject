package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.TypeCultureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TypeCulture and its DTO TypeCultureDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeCultureMapper extends EntityMapper<TypeCultureDTO, TypeCulture> {


    @Mapping(target = "typeCultures", ignore = true)
    TypeCulture toEntity(TypeCultureDTO typeCultureDTO);

    default TypeCulture fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeCulture typeCulture = new TypeCulture();
        typeCulture.setId(id);
        return typeCulture;
    }
}
