package com.arsatoll.app.web.rest;


import com.arsatoll.app.service.ArsatollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArsatollRessource {


    private final Logger log = LoggerFactory.getLogger(ArsatollRessource.class);

    private static final String ENTITY_NAME = "Arsatool";

    private ArsatollService arsatollService;


    public ArsatollRessource(ArsatollService arsatollService){
        this.arsatollService = arsatollService;
    }

    @GetMapping("/listInsecte/{cultureId}/{local}/{type}/{insecteid}")
    public List<String> listRavageur(@PathVariable("cultureId") Long cultureId,@PathVariable("local") String localisation,@PathVariable("type") String typeDegat,@PathVariable("insecteid") Long insecteId){

        log.debug("REST request to get Ravageurs : {}", cultureId,localisation,typeDegat,insecteId);
        return  arsatollService.ravageurs(cultureId,localisation,typeDegat,insecteId);

    }


}
