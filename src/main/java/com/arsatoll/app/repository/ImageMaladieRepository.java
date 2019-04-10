package com.arsatoll.app.repository;

import com.arsatoll.app.domain.ImageMaladie;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ImageMaladie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImageMaladieRepository extends JpaRepository<ImageMaladie, Long> {

}
