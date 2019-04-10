package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.AttaqueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Attaque and its DTO AttaqueDTO.
 */
@Mapper(componentModel = "spring", uses = {InsecteMapper.class, CultureMapper.class, ChercheurMapper.class, TypeDegatMapper.class})
public interface AttaqueMapper extends EntityMapper<AttaqueDTO, Attaque> {

    @Mapping(source = "insecte.id", target = "insecteId")
    @Mapping(source = "culture.id", target = "cultureId")
    @Mapping(source = "chercheur.id", target = "chercheurId")
    @Mapping(source = "typeDegat.id", target = "typeDegatId")
    AttaqueDTO toDto(Attaque attaque);

    @Mapping(source = "insecteId", target = "insecte")
    @Mapping(source = "cultureId", target = "culture")
    @Mapping(target = "attaques", ignore = true)
    @Mapping(source = "chercheurId", target = "chercheur")
    @Mapping(source = "typeDegatId", target = "typeDegat")
    Attaque toEntity(AttaqueDTO attaqueDTO);

    default Attaque fromId(Long id) {
        if (id == null) {
            return null;
        }
        Attaque attaque = new Attaque();
        attaque.setId(id);
        return attaque;
    }
}
