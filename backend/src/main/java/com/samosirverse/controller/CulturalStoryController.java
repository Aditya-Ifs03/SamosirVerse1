package com.samosirverse.controller;

import com.samosirverse.dto.ApiResponse;
import com.samosirverse.entity.CulturalStory;
import com.samosirverse.service.CulturalStoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cultural-stories")
public class CulturalStoryController {

    private final CulturalStoryService storyService;

    public CulturalStoryController(CulturalStoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CulturalStory>>> getAll(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search) {
        List<CulturalStory> list = storyService.getAll(category, search);
        return ResponseEntity.ok(ApiResponse.ok("Katalog cerita budaya Batak Toba", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CulturalStory>> getById(@PathVariable Long id) {
        CulturalStory story = storyService.getById(id);
        return ResponseEntity.ok(ApiResponse.ok("Artikel cerita budaya", story));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ApiResponse<CulturalStory>> getBySlug(@PathVariable String slug) {
        CulturalStory story = storyService.getBySlug(slug);
        return ResponseEntity.ok(ApiResponse.ok("Artikel cerita budaya", story));
    }
}
