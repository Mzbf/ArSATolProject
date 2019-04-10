package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.ImageCultureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ImageCulture and its DTO ImageCultureDTO.
 */
@Mapper(componentModel = "spring", uses = {CultureMapper.class})
public interface ImageCultureMapper extends EntityMapper<ImageCultureDTO, ImageCulture> {

    @Mapping(source = "culture.id", target = "cultureId")
    ImageCultureDTO toDto(ImageCulture imageCulture);

    @Mapping(source = "cultureId", target = "culture")
    ImageCulture toEntity(ImageCultureDTO imageCultureDTO);

    default ImageCulture fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImageCulture imageCulture = new ImageCulture();
        imageCulture.setId(id);
        return imageCulture;
    }
}
