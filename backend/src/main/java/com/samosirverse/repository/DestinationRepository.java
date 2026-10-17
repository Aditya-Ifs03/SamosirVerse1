package com.samosirverse.repository;

import com.samosirverse.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Optional<Destination> findBySlug(String slug);
    List<Destination> findByCategory(String category);
    List<Destination> findBySubDistrict(String subDistrict);
    List<Destination> findByIsFeaturedTrue();

    @Query("SELECT d FROM Destination d WHERE " +
           "(:category IS NULL OR :category = '' OR d.category = :category) AND " +
           "(:subDistrict IS NULL OR :subDistrict = '' OR d.subDistrict = :subDistrict) AND " +
           "(:search IS NULL OR :search = '' OR " +
           " LOWER(d.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           " LOWER(d.description) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           " LOWER(d.shortDescription) LIKE LOWER(CONCAT('%', :search, '%')))")
    List<Destination> filterDestinations(
            @Param("category") String category,
            @Param("subDistrict") String subDistrict,
            @Param("search") String search);
}
