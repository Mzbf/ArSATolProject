package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.ImageInsecteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ImageInsecte and its DTO ImageInsecteDTO.
 */
@Mapper(componentModel = "spring", uses = {InsecteMapper.class})
public interface ImageInsecteMapper extends EntityMapper<ImageInsecteDTO, ImageInsecte> {

    @Mapping(source = "insecte.id", target = "insecteId")
    ImageInsecteDTO toDto(ImageInsecte imageInsecte);

    @Mapping(source = "insecteId", target = "insecte")
    ImageInsecte toEntity(ImageInsecteDTO imageInsecteDTO);

    default ImageInsecte fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImageInsecte imageInsecte = new ImageInsecte();
        imageInsecte.setId(id);
        return imageInsecte;
    }
}
