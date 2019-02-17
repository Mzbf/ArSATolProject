package com.arsatoll.app.repository;

import com.arsatoll.app.domain.SuperFamille;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SuperFamille entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SuperFamilleRepository extends JpaRepository<SuperFamille, Long> {

}
