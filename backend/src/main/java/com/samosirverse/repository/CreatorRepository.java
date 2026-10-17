package com.samosirverse.repository;

import com.samosirverse.entity.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreatorRepository extends JpaRepository<Creator, Long> {
    Optional<Creator> findBySlug(String slug);
    List<Creator> findByVillageContainingIgnoreCase(String village);

    @Query("SELECT c FROM Creator c WHERE " +
           "(:craftType IS NULL OR :craftType = '' OR LOWER(c.craftType) LIKE LOWER(CONCAT('%', :craftType, '%'))) AND " +
           "(:search IS NULL OR :search = '' OR " +
           " LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           " LOWER(c.village) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           " LOWER(c.bio) LIKE LOWER(CONCAT('%', :search, '%')))")
    List<Creator> filterCreators(@Param("craftType") String craftType, @Param("search") String search);
}
