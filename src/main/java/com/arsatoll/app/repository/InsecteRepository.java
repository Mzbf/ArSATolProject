package com.arsatoll.app.repository;

import com.arsatoll.app.domain.Insecte;
import com.arsatoll.app.domain.enumeration.Localisation;
import com.arsatoll.app.service.dto.InsecteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Spring Data  repository for the Insecte entity.
 */

@SuppressWarnings("unused")
@Repository
public interface InsecteRepository extends JpaRepository<Insecte, Long> {

    @Query("SELECT insecte FROM Insecte insecte ,Attaque attaque WHERE attaque.localisation=:local and attaque.culture.id=:id and  attaque.insecte=insecte.id")
    Insecte findRavageur(@Param("id") Long id, @Param("local") Localisation local);

}
