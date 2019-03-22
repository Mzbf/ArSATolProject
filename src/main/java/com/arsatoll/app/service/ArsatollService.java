package com.arsatoll.app.service;

import com.arsatoll.app.domain.Insecte;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArsatollService {

    @Query("select nom_insecte from insecte I, attaque A where A.culture_id =:culture_id and A.localisation=:localisation and A.type_degat =:type_deg and A.insecte_id =:insecte_id ")
    Insecte findRavageur(@Param("culture_id")Long cultureId, @Param("localisation")String localisation, @Param("type_deg") String typeDegat, @Param("insecte_id") Long insecteId);

}
