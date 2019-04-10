package com.arsatoll.app.repository;

import com.arsatoll.app.domain.ImageAttaque;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ImageAttaque entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImageAttaqueRepository extends JpaRepository<ImageAttaque, Long> {

}
