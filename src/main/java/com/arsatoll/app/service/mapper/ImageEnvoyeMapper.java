package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.ImageEnvoyeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ImageEnvoye and its DTO ImageEnvoyeDTO.
 */
@Mapper(componentModel = "spring", uses = {AgriculteurMapper.class})
public interface ImageEnvoyeMapper extends EntityMapper<ImageEnvoyeDTO, ImageEnvoye> {

    @Mapping(source = "agriculteur.id", target = "agriculteurId")
    ImageEnvoyeDTO toDto(ImageEnvoye imageEnvoye);

    @Mapping(source = "agriculteurId", target = "agriculteur")
    ImageEnvoye toEntity(ImageEnvoyeDTO imageEnvoyeDTO);

    default ImageEnvoye fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImageEnvoye imageEnvoye = new ImageEnvoye();
        imageEnvoye.setId(id);
        return imageEnvoye;
    }
}
