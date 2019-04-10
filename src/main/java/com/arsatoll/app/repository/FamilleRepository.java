package com.arsatoll.app.repository;

import com.arsatoll.app.domain.Famille;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Famille entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FamilleRepository extends JpaRepository<Famille, Long> {

}
