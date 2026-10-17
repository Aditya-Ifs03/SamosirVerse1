package com.samosirverse.repository;

import com.samosirverse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySlug(String slug);
    List<Product> findByCreatorId(Long creatorId);
    List<Product> findByCategory(String category);

    @Query("SELECT p FROM Product p WHERE " +
           "(:category IS NULL OR :category = '' OR p.category = :category) AND " +
           "(:creatorId IS NULL OR p.creator.id = :creatorId) AND " +
           "(:search IS NULL OR :search = '' OR " +
           " LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           " LOWER(p.description) LIKE LOWER(CONCAT('%', :search, '%')))")
    List<Product> filterProducts(
            @Param("category") String category,
            @Param("creatorId") Long creatorId,
            @Param("search") String search);
}
