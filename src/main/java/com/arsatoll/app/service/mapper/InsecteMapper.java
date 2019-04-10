package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.InsecteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Insecte and its DTO InsecteDTO.
 */
@Mapper(componentModel = "spring", uses = {TypeInsecteMapper.class})
public interface InsecteMapper extends EntityMapper<InsecteDTO, Insecte> {

    @Mapping(source = "typeInsecte.id", target = "typeInsecteId")
    InsecteDTO toDto(Insecte insecte);

    @Mapping(target = "insectes", ignore = true)
    @Mapping(target = "listattaques", ignore = true)
    @Mapping(source = "typeInsecteId", target = "typeInsecte")
    Insecte toEntity(InsecteDTO insecteDTO);

    default Insecte fromId(Long id) {
        if (id == null) {
            return null;
        }
        Insecte insecte = new Insecte();
        insecte.setId(id);
        return insecte;
    }
}
