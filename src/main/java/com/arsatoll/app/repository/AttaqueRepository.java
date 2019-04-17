package com.arsatoll.app.repository;

import com.arsatoll.app.domain.Attaque;
import com.arsatoll.app.domain.enumeration.Localisation;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Attaque entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttaqueRepository extends JpaRepository<Attaque, Long> {

    @Query("SELECT attaque FROM Attaque attaque WHERE attaque.culture.id=:culture and attaque.localisation=:local")
    List<Attaque> listeAttaque(@Param("culture") Long culture,@Param("local") Localisation local);

    @Query("SELECT attaque FROM Attaque attaque, Insecte I WHERE attaque.culture.id=:cultureid and attaque.localisation=:local and attaque.insecte.id=I.id")
    List<Object> attaqueImage(@Param("cultureid") Long cultureid, @Param("local") Localisation local);
}
