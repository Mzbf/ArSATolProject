package com.arsatoll.app.repository;

import com.arsatoll.app.domain.ImageAttaque;
import com.arsatoll.app.service.dto.ImageAttaqueDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the ImageAttaque entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImageAttaqueRepository extends JpaRepository<ImageAttaque, Long> {

    @Query("select image from ImageAttaque image where image.attaque.id=:id")
    List<ImageAttaque> listImageAttaque(@Param("id") Long id);
}
