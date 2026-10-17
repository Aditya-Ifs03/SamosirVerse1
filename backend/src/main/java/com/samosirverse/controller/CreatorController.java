package com.samosirverse.controller;

import com.samosirverse.dto.ApiResponse;
import com.samosirverse.entity.Creator;
import com.samosirverse.service.CreatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creators")
public class CreatorController {

    private final CreatorService creatorService;

    public CreatorController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Creator>>> getAll(
            @RequestParam(required = false) String craftType,
            @RequestParam(required = false) String search) {
        List<Creator> list = creatorService.getAll(craftType, search);
        return ResponseEntity.ok(ApiResponse.ok("Daftar kreator lokal Samosir", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Creator>> getById(@PathVariable Long id) {
        Creator creator = creatorService.getById(id);
        return ResponseEntity.ok(ApiResponse.ok("Profil kreator lokal", creator));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ApiResponse<Creator>> getBySlug(@PathVariable String slug) {
        Creator creator = creatorService.getBySlug(slug);
        return ResponseEntity.ok(ApiResponse.ok("Profil kreator lokal", creator));
    }
}
