package com.arsatoll.app.repository;

import com.arsatoll.app.domain.ZoneGeo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ZoneGeo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ZoneGeoRepository extends JpaRepository<ZoneGeo, Long> {

}
