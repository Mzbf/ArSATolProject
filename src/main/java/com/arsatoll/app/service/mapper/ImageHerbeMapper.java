package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.ImageHerbeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ImageHerbe and its DTO ImageHerbeDTO.
 */
@Mapper(componentModel = "spring", uses = {HerbeMapper.class})
public interface ImageHerbeMapper extends EntityMapper<ImageHerbeDTO, ImageHerbe> {

    @Mapping(source = "herbe.id", target = "herbeId")
    ImageHerbeDTO toDto(ImageHerbe imageHerbe);

    @Mapping(source = "herbeId", target = "herbe")
    ImageHerbe toEntity(ImageHerbeDTO imageHerbeDTO);

    default ImageHerbe fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImageHerbe imageHerbe = new ImageHerbe();
        imageHerbe.setId(id);
        return imageHerbe;
    }
}
