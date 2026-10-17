package com.samosirverse.controller;

import com.samosirverse.dto.*;
import com.samosirverse.entity.*;
import com.samosirverse.repository.*;
import com.samosirverse.service.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final DestinationService destinationService;
    private final CreatorService creatorService;
    private final ProductService productService;
    private final CulturalStoryService storyService;
    private final ExperienceService experienceService;

    private final DestinationRepository destinationRepository;
    private final CreatorRepository creatorRepository;
    private final ProductRepository productRepository;
    private final CulturalStoryRepository storyRepository;
    private final ExperienceRepository experienceRepository;

    public AdminController(
            DestinationService destinationService,
            CreatorService creatorService,
            ProductService productService,
            CulturalStoryService storyService,
            ExperienceService experienceService,
            DestinationRepository destinationRepository,
            CreatorRepository creatorRepository,
            ProductRepository productRepository,
            CulturalStoryRepository storyRepository,
            ExperienceRepository experienceRepository) {
        this.destinationService = destinationService;
        this.creatorService = creatorService;
        this.productService = productService;
        this.storyService = storyService;
        this.experienceService = experienceService;
        this.destinationRepository = destinationRepository;
        this.creatorRepository = creatorRepository;
        this.productRepository = productRepository;
        this.storyRepository = storyRepository;
        this.experienceRepository = experienceRepository;
    }

    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<DashboardStatsResponse>> getStats() {
        DashboardStatsResponse stats = new DashboardStatsResponse(
                destinationRepository.count(),
                creatorRepository.count(),
                productRepository.count(),
                storyRepository.count(),
                experienceRepository.count()
        );
        return ResponseEntity.ok(ApiResponse.ok("Ringkasan data dashboard admin", stats));
    }

    // --- Destination CRUD ---
    @PostMapping("/destinations")
    public ResponseEntity<ApiResponse<Destination>> createDestination(@Valid @RequestBody DestinationRequest request) {
        Destination created = destinationService.create(request);
        return ResponseEntity.ok(ApiResponse.ok("Destinasi berhasil ditambahkan", created));
    }

    @PutMapping("/destinations/{id}")
    public ResponseEntity<ApiResponse<Destination>> updateDestination(@PathVariable Long id, @Valid @RequestBody DestinationRequest request) {
        Destination updated = destinationService.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Destinasi berhasil diperbarui", updated));
    }

    @DeleteMapping("/destinations/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDestination(@PathVariable Long id) {
        destinationService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Destinasi berhasil dihapus", null));
    }

    // --- Creator CRUD ---
    @PostMapping("/creators")
    public ResponseEntity<ApiResponse<Creator>> createCreator(@Valid @RequestBody CreatorRequest request) {
        Creator created = creatorService.create(request);
        return ResponseEntity.ok(ApiResponse.ok("Kreator lokal berhasil ditambahkan", created));
    }

    @PutMapping("/creators/{id}")
    public ResponseEntity<ApiResponse<Creator>> updateCreator(@PathVariable Long id, @Valid @RequestBody CreatorRequest request) {
        Creator updated = creatorService.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Kreator lokal berhasil diperbarui", updated));
    }

    @DeleteMapping("/creators/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCreator(@PathVariable Long id) {
        creatorService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Kreator lokal berhasil dihapus", null));
    }

    // --- Product CRUD ---
    @PostMapping("/products")
    public ResponseEntity<ApiResponse<Product>> createProduct(@Valid @RequestBody ProductRequest request) {
        Product created = productService.create(request);
        return ResponseEntity.ok(ApiResponse.ok("Produk UMKM berhasil ditambahkan", created));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        Product updated = productService.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Produk UMKM berhasil diperbarui", updated));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Produk UMKM berhasil dihapus", null));
    }

    // --- Cultural Story CRUD ---
    @PostMapping("/cultural-stories")
    public ResponseEntity<ApiResponse<CulturalStory>> createStory(@Valid @RequestBody CulturalStoryRequest request) {
        CulturalStory created = storyService.create(request);
        return ResponseEntity.ok(ApiResponse.ok("Cerita budaya berhasil ditambahkan", created));
    }

    @PutMapping("/cultural-stories/{id}")
    public ResponseEntity<ApiResponse<CulturalStory>> updateStory(@PathVariable Long id, @Valid @RequestBody CulturalStoryRequest request) {
        CulturalStory updated = storyService.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Cerita budaya berhasil diperbarui", updated));
    }

    @DeleteMapping("/cultural-stories/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStory(@PathVariable Long id) {
        storyService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Cerita budaya berhasil dihapus", null));
    }

    // --- Experience CRUD ---
    @PostMapping("/experiences")
    public ResponseEntity<ApiResponse<Experience>> createExperience(@Valid @RequestBody ExperienceRequest request) {
        Experience created = experienceService.create(request);
        return ResponseEntity.ok(ApiResponse.ok("Pengalaman/agenda berhasil ditambahkan", created));
    }

    @PutMapping("/experiences/{id}")
    public ResponseEntity<ApiResponse<Experience>> updateExperience(@PathVariable Long id, @Valid @RequestBody ExperienceRequest request) {
        Experience updated = experienceService.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Pengalaman/agenda berhasil diperbarui", updated));
    }

    @DeleteMapping("/experiences/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteExperience(@PathVariable Long id) {
        experienceService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Pengalaman/agenda berhasil dihapus", null));
    }
}
