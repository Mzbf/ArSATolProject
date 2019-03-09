package com.arsatoll.app.service;

import java.util.List;

public interface ArsatollService {

    List<String> ravageurs(Long cultureId, String localisation, String typeDegat, Long insecteId);

}
