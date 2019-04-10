package com.arsatoll.app.repository;

import com.arsatoll.app.domain.Agriculteur;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Agriculteur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgriculteurRepository extends JpaRepository<Agriculteur, Long> {

}
