package com.arsatoll.app.repository;

import com.arsatoll.app.domain.Culture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Culture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CultureRepository extends JpaRepository<Culture, Long> {

    @Query(value = "select distinct culture from Culture culture left join fetch culture.maladies left join fetch culture.herbes left join fetch culture.zones",
        countQuery = "select count(distinct culture) from Culture culture")
    Page<Culture> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct culture from Culture culture left join fetch culture.maladies left join fetch culture.herbes left join fetch culture.zones")
    List<Culture> findAllWithEagerRelationships();

    @Query("select culture from Culture culture left join fetch culture.maladies left join fetch culture.herbes left join fetch culture.zones where culture.id =:id")
    Optional<Culture> findOneWithEagerRelationships(@Param("id") Long id);

    @Query("select culture , image.imageUrl from Culture culture, ImageCulture image where culture.id=image.culture.id")
    List<Object> cultureImage();

}
