package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.ImageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Image and its DTO ImageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ImageMapper extends EntityMapper<ImageDTO, Image> {



    default Image fromId(Long id) {
        if (id == null) {
            return null;
        }
        Image image = new Image();
        image.setId(id);
        return image;
    }
}
