package com.samosirverse.repository;

import com.samosirverse.entity.CulturalStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CulturalStoryRepository extends JpaRepository<CulturalStory, Long> {
    Optional<CulturalStory> findBySlug(String slug);
    List<CulturalStory> findByCategory(String category);

    @Query("SELECT s FROM CulturalStory s WHERE " +
           "(:category IS NULL OR :category = '' OR s.category = :category) AND " +
           "(:search IS NULL OR :search = '' OR " +
           " LOWER(s.title) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           " LOWER(s.summary) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           " LOWER(s.content) LIKE LOWER(CONCAT('%', :search, '%')))")
    List<CulturalStory> filterStories(@Param("category") String category, @Param("search") String search);
}
