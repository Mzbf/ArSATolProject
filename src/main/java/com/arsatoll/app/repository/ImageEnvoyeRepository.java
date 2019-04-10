package com.arsatoll.app.repository;

import com.arsatoll.app.domain.ImageEnvoye;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ImageEnvoye entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImageEnvoyeRepository extends JpaRepository<ImageEnvoye, Long> {

}
