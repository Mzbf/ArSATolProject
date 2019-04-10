package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.ImageMaladieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ImageMaladie and its DTO ImageMaladieDTO.
 */
@Mapper(componentModel = "spring", uses = {MaladieMapper.class})
public interface ImageMaladieMapper extends EntityMapper<ImageMaladieDTO, ImageMaladie> {

    @Mapping(source = "maladie.id", target = "maladieId")
    ImageMaladieDTO toDto(ImageMaladie imageMaladie);

    @Mapping(source = "maladieId", target = "maladie")
    ImageMaladie toEntity(ImageMaladieDTO imageMaladieDTO);

    default ImageMaladie fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImageMaladie imageMaladie = new ImageMaladie();
        imageMaladie.setId(id);
        return imageMaladie;
    }
}
