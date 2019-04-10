package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.ZoneGeoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ZoneGeo and its DTO ZoneGeoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ZoneGeoMapper extends EntityMapper<ZoneGeoDTO, ZoneGeo> {


    @Mapping(target = "cultures", ignore = true)
    ZoneGeo toEntity(ZoneGeoDTO zoneGeoDTO);

    default ZoneGeo fromId(Long id) {
        if (id == null) {
            return null;
        }
        ZoneGeo zoneGeo = new ZoneGeo();
        zoneGeo.setId(id);
        return zoneGeo;
    }
}
