package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.DemoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Demo and its DTO DemoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DemoMapper extends EntityMapper<DemoDTO, Demo> {



    default Demo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Demo demo = new Demo();
        demo.setId(id);
        return demo;
    }
}
