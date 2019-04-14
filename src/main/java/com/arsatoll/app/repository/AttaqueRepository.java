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

    @Query("SELECT A.description as desc, I.nomInsecte as nominsecte, imageattaque.imageUrl as image FROM Attaque A, Insecte I, ImageAttaque imageattaque WHERE A.culture.id=:cultureid and A.localisation=:local and A.id=imageattaque.attaque.id and A.insecte.id=I.id")
    List<Object> attaqueImage(@Param("cultureid") Long cultureid, @Param("local") Localisation local);
}
