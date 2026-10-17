package com.samosirverse.service;

import com.samosirverse.dto.ProductRequest;
import com.samosirverse.entity.Creator;
import com.samosirverse.entity.Product;
import com.samosirverse.exception.ResourceNotFoundException;
import com.samosirverse.repository.CreatorRepository;
import com.samosirverse.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CreatorRepository creatorRepository;

    public ProductService(ProductRepository productRepository, CreatorRepository creatorRepository) {
        this.productRepository = productRepository;
        this.creatorRepository = creatorRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> getAll(String category, Long creatorId, String search) {
        return productRepository.filterProducts(category, creatorId, search);
    }

    @Transactional(readOnly = true)
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produk dengan ID " + id + " tidak ditemukan"));
    }

    @Transactional(readOnly = true)
    public Product getBySlug(String slug) {
        return productRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Produk dengan slug '" + slug + "' tidak ditemukan"));
    }

    public Product create(ProductRequest request) {
        Creator creator = creatorRepository.findById(request.getCreatorId())
                .orElseThrow(() -> new ResourceNotFoundException("Kreator dengan ID " + request.getCreatorId() + " tidak ditemukan"));

        Product product = new Product();
        product.setCreator(creator);
        mapRequestToEntity(request, product);
        String baseSlug = SlugUtils.toSlug(request.getName());
        product.setSlug(ensureUniqueSlug(baseSlug, null));
        return productRepository.save(product);
    }

    public Product update(Long id, ProductRequest request) {
        Product product = getById(id);
        if (!product.getCreator().getId().equals(request.getCreatorId())) {
            Creator creator = creatorRepository.findById(request.getCreatorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Kreator dengan ID " + request.getCreatorId() + " tidak ditemukan"));
            product.setCreator(creator);
        }
        mapRequestToEntity(request, product);
        String baseSlug = SlugUtils.toSlug(request.getName());
        product.setSlug(ensureUniqueSlug(baseSlug, id));
        return productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = getById(id);
        productRepository.delete(product);
    }

    private void mapRequestToEntity(ProductRequest req, Product product) {
        product.setName(req.getName());
        product.setCategory(req.getCategory());
        product.setPrice(req.getPrice());
        product.setDescription(req.getDescription());
        product.setImageUrl(req.getImageUrl());
        product.setStockStatus(req.getStockStatus() != null ? req.getStockStatus() : "TERSEDIA");
        product.setIsAuthenticHandcrafted(req.getIsAuthenticHandcrafted() != null ? req.getIsAuthenticHandcrafted() : true);
    }

    private String ensureUniqueSlug(String baseSlug, Long excludeId) {
        String slug = baseSlug;
        int counter = 1;
        while (true) {
            var existing = productRepository.findBySlug(slug);
            if (existing.isEmpty() || (excludeId != null && existing.get().getId().equals(excludeId))) {
                return slug;
            }
            slug = baseSlug + "-" + counter++;
        }
    }
}
