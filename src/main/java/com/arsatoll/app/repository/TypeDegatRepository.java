package com.arsatoll.app.repository;

import com.arsatoll.app.domain.TypeDegat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeDegat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeDegatRepository extends JpaRepository<TypeDegat, Long> {

}
