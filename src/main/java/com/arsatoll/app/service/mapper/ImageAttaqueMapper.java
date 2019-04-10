package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.ImageAttaqueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ImageAttaque and its DTO ImageAttaqueDTO.
 */
@Mapper(componentModel = "spring", uses = {AttaqueMapper.class})
public interface ImageAttaqueMapper extends EntityMapper<ImageAttaqueDTO, ImageAttaque> {

    @Mapping(source = "attaque.id", target = "attaqueId")
    ImageAttaqueDTO toDto(ImageAttaque imageAttaque);

    @Mapping(source = "attaqueId", target = "attaque")
    ImageAttaque toEntity(ImageAttaqueDTO imageAttaqueDTO);

    default ImageAttaque fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImageAttaque imageAttaque = new ImageAttaque();
        imageAttaque.setId(id);
        return imageAttaque;
    }
}
