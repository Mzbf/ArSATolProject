package com.arsatoll.app.repository;

import com.arsatoll.app.domain.Attaque;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Attaque entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttaqueRepository extends JpaRepository<Attaque, Long> {

}
