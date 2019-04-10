package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.TypeInsecteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TypeInsecte and its DTO TypeInsecteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeInsecteMapper extends EntityMapper<TypeInsecteDTO, TypeInsecte> {


    @Mapping(target = "typeInsectes", ignore = true)
    TypeInsecte toEntity(TypeInsecteDTO typeInsecteDTO);

    default TypeInsecte fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeInsecte typeInsecte = new TypeInsecte();
        typeInsecte.setId(id);
        return typeInsecte;
    }
}
