package com.arsatoll.app.repository;

import com.arsatoll.app.domain.ImageHerbe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ImageHerbe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImageHerbeRepository extends JpaRepository<ImageHerbe, Long> {

}
