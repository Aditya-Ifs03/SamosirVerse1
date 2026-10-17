package com.samosirverse.repository;

import com.samosirverse.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    Optional<Experience> findBySlug(String slug);
    List<Experience> findByType(String type);

    @Query("SELECT e FROM Experience e WHERE " +
           "(:type IS NULL OR :type = '' OR e.type = :type) AND " +
           "(:search IS NULL OR :search = '' OR " +
           " LOWER(e.title) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           " LOWER(e.location) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           " LOWER(e.description) LIKE LOWER(CONCAT('%', :search, '%')))")
    List<Experience> filterExperiences(@Param("type") String type, @Param("search") String search);
}
