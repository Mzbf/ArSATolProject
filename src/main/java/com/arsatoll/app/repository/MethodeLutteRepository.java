package com.arsatoll.app.repository;

import com.arsatoll.app.domain.MethodeLutte;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MethodeLutte entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MethodeLutteRepository extends JpaRepository<MethodeLutte, Long> {

}
