package com.arsatoll.app.repository;

import com.arsatoll.app.domain.Maladie;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Maladie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MaladieRepository extends JpaRepository<Maladie, Long> {

}
