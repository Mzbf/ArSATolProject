package com.arsatoll.app.repository;

import com.arsatoll.app.domain.ImageCulture;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ImageCulture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImageCultureRepository extends JpaRepository<ImageCulture, Long> {

}
