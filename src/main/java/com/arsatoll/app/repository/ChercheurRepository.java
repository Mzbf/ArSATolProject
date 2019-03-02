package com.arsatoll.app.repository;

import com.arsatoll.app.domain.Chercheur;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Chercheur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChercheurRepository extends JpaRepository<Chercheur, Long> {

}
