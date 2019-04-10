package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.CultureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Culture and its DTO CultureDTO.
 */
@Mapper(componentModel = "spring", uses = {MaladieMapper.class, HerbeMapper.class, ZoneGeoMapper.class, TypeCultureMapper.class})
public interface CultureMapper extends EntityMapper<CultureDTO, Culture> {

    @Mapping(source = "typeCulture.id", target = "typeCultureId")
    CultureDTO toDto(Culture culture);

    @Mapping(target = "imagecultures", ignore = true)
    @Mapping(target = "listattaques", ignore = true)
    @Mapping(source = "typeCultureId", target = "typeCulture")
    Culture toEntity(CultureDTO cultureDTO);

    default Culture fromId(Long id) {
        if (id == null) {
            return null;
        }
        Culture culture = new Culture();
        culture.setId(id);
        return culture;
    }
}
