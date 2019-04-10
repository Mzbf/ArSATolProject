package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.MethodeLutteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MethodeLutte and its DTO MethodeLutteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MethodeLutteMapper extends EntityMapper<MethodeLutteDTO, MethodeLutte> {



    default MethodeLutte fromId(Long id) {
        if (id == null) {
            return null;
        }
        MethodeLutte methodeLutte = new MethodeLutte();
        methodeLutte.setId(id);
        return methodeLutte;
    }
}
