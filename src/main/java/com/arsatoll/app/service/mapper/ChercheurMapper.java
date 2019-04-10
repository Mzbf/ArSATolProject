package com.arsatoll.app.service.mapper;

import com.arsatoll.app.domain.*;
import com.arsatoll.app.service.dto.ChercheurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Chercheur and its DTO ChercheurDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ChercheurMapper extends EntityMapper<ChercheurDTO, Chercheur> {

    @Mapping(source = "user.id", target = "userId")
    ChercheurDTO toDto(Chercheur chercheur);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "ajoutAttaques", ignore = true)
    Chercheur toEntity(ChercheurDTO chercheurDTO);

    default Chercheur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Chercheur chercheur = new Chercheur();
        chercheur.setId(id);
        return chercheur;
    }
}
