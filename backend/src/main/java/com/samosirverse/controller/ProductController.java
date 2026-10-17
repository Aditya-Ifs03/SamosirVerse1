package com.samosirverse.controller;

import com.samosirverse.dto.ApiResponse;
import com.samosirverse.entity.Product;
import com.samosirverse.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAll(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Long creatorId,
            @RequestParam(required = false) String search) {
        List<Product> list = productService.getAll(category, creatorId, search);
        return ResponseEntity.ok(ApiResponse.ok("Katalog produk UMKM Samosir", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return ResponseEntity.ok(ApiResponse.ok("Detail produk UMKM", product));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ApiResponse<Product>> getBySlug(@PathVariable String slug) {
        Product product = productService.getBySlug(slug);
        return ResponseEntity.ok(ApiResponse.ok("Detail produk UMKM", product));
    }
}
