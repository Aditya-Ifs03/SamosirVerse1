package com.samosirverse.controller;

import com.samosirverse.dto.ApiResponse;
import com.samosirverse.entity.Experience;
import com.samosirverse.service.ExperienceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Experience>>> getAll(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String search) {
        List<Experience> list = experienceService.getAll(type, search);
        return ResponseEntity.ok(ApiResponse.ok("Katalog agenda dan pengalaman lokal Samosir", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Experience>> getById(@PathVariable Long id) {
        Experience experience = experienceService.getById(id);
        return ResponseEntity.ok(ApiResponse.ok("Detail pengalaman/agenda", experience));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ApiResponse<Experience>> getBySlug(@PathVariable String slug) {
        Experience experience = experienceService.getBySlug(slug);
        return ResponseEntity.ok(ApiResponse.ok("Detail pengalaman/agenda", experience));
    }
}
