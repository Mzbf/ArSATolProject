package com.arsatoll.app.repository;

import com.arsatoll.app.domain.ImageInsecte;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ImageInsecte entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImageInsecteRepository extends JpaRepository<ImageInsecte, Long> {

}
