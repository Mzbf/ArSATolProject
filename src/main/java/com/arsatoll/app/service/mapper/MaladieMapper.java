package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.MaladieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Maladie and its DTO MaladieDTO.
 */
@Mapper(componentModel = "spring", uses = {MethodeLutteMapper.class})
public interface MaladieMapper extends EntityMapper<MaladieDTO, Maladie> {

    @Mapping(source = "maladieML.id", target = "maladieMLId")
    MaladieDTO toDto(Maladie maladie);

    @Mapping(source = "maladieMLId", target = "maladieML")
    @Mapping(target = "maladies", ignore = true)
    @Mapping(target = "cultures", ignore = true)
    Maladie toEntity(MaladieDTO maladieDTO);

    default Maladie fromId(Long id) {
        if (id == null) {
            return null;
        }
        Maladie maladie = new Maladie();
        maladie.setId(id);
        return maladie;
    }
}
