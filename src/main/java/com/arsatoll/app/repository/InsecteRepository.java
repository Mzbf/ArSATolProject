package com.arsatoll.app.repository;

import com.arsatoll.app.domain.Insecte;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Insecte entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsecteRepository extends JpaRepository<Insecte, Long> {

}
