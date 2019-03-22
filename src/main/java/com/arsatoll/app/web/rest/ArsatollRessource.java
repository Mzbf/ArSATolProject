package com.arsatoll.app.web.rest;


import com.arsatoll.app.domain.Insecte;
import com.arsatoll.app.service.ArsatollService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ArsatollRessource {


    private final Logger log = LoggerFactory.getLogger(ArsatollRessource.class);

    private static final String ENTITY_NAME = "Arsatool";


    private ArsatollService arsatollService;


    @GetMapping("/insectes/{cultureId}/{local}/{type}/{insecteid}")
    public ResponseEntity<Insecte> findRavageur(@RequestParam Long cultureId, @RequestParam String localisation, @RequestParam String typeDegat, @RequestParam Long insecteId){

        Optional<Insecte> insecte = Optional.ofNullable(arsatollService.findRavageur(cultureId, localisation, typeDegat, insecteId));
        return ResponseUtil.wrapOrNotFound(insecte);
    }

   /* @GetMapping("/insectes/{cultureId}/{local}/{type}/{insecteid}")
    public List<String> listRavageur(@PathVariable("cultureId") Long cultureId,@PathVariable("local") String localisation,@PathVariable("type") String typeDegat,@PathVariable("insecteid") Long insecteId){

        log.debug("REST request to get Ravageurs : {}", cultureId,localisation,typeDegat,insecteId);
        return  arsatollService.ravageurs(cultureId,localisation,typeDegat,insecteId);

    }*/


}
