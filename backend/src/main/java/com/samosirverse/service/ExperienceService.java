package com.samosirverse.service;

import com.samosirverse.dto.ExperienceRequest;
import com.samosirverse.entity.Experience;
import com.samosirverse.exception.ResourceNotFoundException;
import com.samosirverse.repository.ExperienceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Transactional(readOnly = true)
    public List<Experience> getAll(String type, String search) {
        return experienceRepository.filterExperiences(type, search);
    }

    @Transactional(readOnly = true)
    public Experience getById(Long id) {
        return experienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pengalaman/agenda dengan ID " + id + " tidak ditemukan"));
    }

    @Transactional(readOnly = true)
    public Experience getBySlug(String slug) {
        return experienceRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Pengalaman/agenda dengan slug '" + slug + "' tidak ditemukan"));
    }

    public Experience create(ExperienceRequest request) {
        Experience exp = new Experience();
        mapRequestToEntity(request, exp);
        String baseSlug = SlugUtils.toSlug(request.getTitle());
        exp.setSlug(ensureUniqueSlug(baseSlug, null));
        return experienceRepository.save(exp);
    }

    public Experience update(Long id, ExperienceRequest request) {
        Experience exp = getById(id);
        mapRequestToEntity(request, exp);
        String baseSlug = SlugUtils.toSlug(request.getTitle());
        exp.setSlug(ensureUniqueSlug(baseSlug, id));
        return experienceRepository.save(exp);
    }

    public void delete(Long id) {
        Experience exp = getById(id);
        experienceRepository.delete(exp);
    }

    private void mapRequestToEntity(ExperienceRequest req, Experience exp) {
        exp.setTitle(req.getTitle());
        exp.setType(req.getType());
        exp.setLocation(req.getLocation());
        exp.setEventDate(req.getEventDate());
        exp.setDuration(req.getDuration());
        exp.setPrice(req.getPrice());
        exp.setDescription(req.getDescription());
        exp.setOrganizerName(req.getOrganizerName());
        // WhatsApp booking number normalization
        String wa = req.getWhatsappBooking().trim();
        if (wa.startsWith("0")) {
            wa = "+62" + wa.substring(1);
        } else if (wa.startsWith("62")) {
            wa = "+" + wa;
        }
        exp.setWhatsappBooking(wa);
        exp.setImageUrl(req.getImageUrl());
        exp.setMaxParticipants(req.getMaxParticipants() != null ? req.getMaxParticipants() : 20);
    }

    private String ensureUniqueSlug(String baseSlug, Long excludeId) {
        String slug = baseSlug;
        int counter = 1;
        while (true) {
            var existing = experienceRepository.findBySlug(slug);
            if (existing.isEmpty() || (excludeId != null && existing.get().getId().equals(excludeId))) {
                return slug;
            }
            slug = baseSlug + "-" + counter++;
        }
    }
}
