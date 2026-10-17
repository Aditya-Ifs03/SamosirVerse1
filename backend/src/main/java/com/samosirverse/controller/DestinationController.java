package com.samosirverse.controller;

import com.samosirverse.dto.ApiResponse;
import com.samosirverse.entity.Destination;
import com.samosirverse.service.DestinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Destination>>> getAll(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String subDistrict,
            @RequestParam(required = false) String search) {
        List<Destination> list = destinationService.getAll(category, subDistrict, search);
        return ResponseEntity.ok(ApiResponse.ok("Daftar destinasi wisata Samosir", list));
    }

    @GetMapping("/featured")
    public ResponseEntity<ApiResponse<List<Destination>>> getFeatured() {
        List<Destination> list = destinationService.getFeatured();
        return ResponseEntity.ok(ApiResponse.ok("Destinasi unggulan Samosir", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Destination>> getById(@PathVariable Long id) {
        Destination destination = destinationService.getById(id);
        return ResponseEntity.ok(ApiResponse.ok("Detail destinasi", destination));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ApiResponse<Destination>> getBySlug(@PathVariable String slug) {
        Destination destination = destinationService.getBySlug(slug);
        return ResponseEntity.ok(ApiResponse.ok("Detail destinasi", destination));
    }
}
