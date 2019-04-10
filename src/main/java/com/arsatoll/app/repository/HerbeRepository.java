package com.arsatoll.app.repository;

import com.arsatoll.app.domain.Herbe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Herbe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HerbeRepository extends JpaRepository<Herbe, Long> {

}
