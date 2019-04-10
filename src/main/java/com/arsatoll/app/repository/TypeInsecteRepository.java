package com.arsatoll.app.repository;

import com.arsatoll.app.domain.TypeInsecte;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeInsecte entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeInsecteRepository extends JpaRepository<TypeInsecte, Long> {

}
