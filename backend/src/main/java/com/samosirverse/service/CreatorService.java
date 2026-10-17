package com.samosirverse.service;

import com.samosirverse.dto.CreatorRequest;
import com.samosirverse.entity.Creator;
import com.samosirverse.exception.ResourceNotFoundException;
import com.samosirverse.repository.CreatorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CreatorService {

    private final CreatorRepository creatorRepository;

    public CreatorService(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    @Transactional(readOnly = true)
    public List<Creator> getAll(String craftType, String search) {
        return creatorRepository.filterCreators(craftType, search);
    }

    @Transactional(readOnly = true)
    public Creator getById(Long id) {
        return creatorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kreator dengan ID " + id + " tidak ditemukan"));
    }

    @Transactional(readOnly = true)
    public Creator getBySlug(String slug) {
        return creatorRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Kreator dengan slug '" + slug + "' tidak ditemukan"));
    }

    public Creator create(CreatorRequest request) {
        Creator creator = new Creator();
        mapRequestToEntity(request, creator);
        String baseSlug = SlugUtils.toSlug(request.getName());
        creator.setSlug(ensureUniqueSlug(baseSlug, null));
        return creatorRepository.save(creator);
    }

    public Creator update(Long id, CreatorRequest request) {
        Creator creator = getById(id);
        mapRequestToEntity(request, creator);
        String baseSlug = SlugUtils.toSlug(request.getName());
        creator.setSlug(ensureUniqueSlug(baseSlug, id));
        return creatorRepository.save(creator);
    }

    public void delete(Long id) {
        Creator creator = getById(id);
        creatorRepository.delete(creator);
    }

    private void mapRequestToEntity(CreatorRequest req, Creator creator) {
        creator.setName(req.getName());
        creator.setCraftType(req.getCraftType());
        creator.setVillage(req.getVillage());
        creator.setBio(req.getBio());
        // Normalize WhatsApp number
        String wa = req.getWhatsappNumber().trim();
        if (wa.startsWith("0")) {
            wa = "+62" + wa.substring(1);
        } else if (wa.startsWith("62")) {
            wa = "+" + wa;
        }
        creator.setWhatsappNumber(wa);
        creator.setIsWhatsappVerified(req.getIsWhatsappVerified() != null ? req.getIsWhatsappVerified() : true);
        creator.setProfileImageUrl(req.getProfileImageUrl());
        creator.setYearsOfExperience(req.getYearsOfExperience() != null ? req.getYearsOfExperience() : 5);
        creator.setAwards(req.getAwards());
    }

    private String ensureUniqueSlug(String baseSlug, Long excludeId) {
        String slug = baseSlug;
        int counter = 1;
        while (true) {
            var existing = creatorRepository.findBySlug(slug);
            if (existing.isEmpty() || (excludeId != null && existing.get().getId().equals(excludeId))) {
                return slug;
            }
            slug = baseSlug + "-" + counter++;
        }
    }
}
