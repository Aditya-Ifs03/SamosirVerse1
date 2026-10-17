package com.samosirverse.service;

import com.samosirverse.dto.DestinationRequest;
import com.samosirverse.entity.Destination;
import com.samosirverse.exception.ResourceNotFoundException;
import com.samosirverse.repository.DestinationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Transactional(readOnly = true)
    public List<Destination> getAll(String category, String subDistrict, String search) {
        return destinationRepository.filterDestinations(category, subDistrict, search);
    }

    @Transactional(readOnly = true)
    public List<Destination> getFeatured() {
        return destinationRepository.findByIsFeaturedTrue();
    }

    @Transactional(readOnly = true)
    public Destination getById(Long id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destinasi dengan ID " + id + " tidak ditemukan"));
    }

    @Transactional(readOnly = true)
    public Destination getBySlug(String slug) {
        return destinationRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Destinasi dengan slug '" + slug + "' tidak ditemukan"));
    }

    public Destination create(DestinationRequest request) {
        Destination destination = new Destination();
        mapRequestToEntity(request, destination);
        String baseSlug = SlugUtils.toSlug(request.getName());
        destination.setSlug(ensureUniqueSlug(baseSlug, null));
        return destinationRepository.save(destination);
    }

    public Destination update(Long id, DestinationRequest request) {
        Destination destination = getById(id);
        mapRequestToEntity(request, destination);
        String baseSlug = SlugUtils.toSlug(request.getName());
        destination.setSlug(ensureUniqueSlug(baseSlug, id));
        return destinationRepository.save(destination);
    }

    public void delete(Long id) {
        Destination destination = getById(id);
        destinationRepository.delete(destination);
    }

    private void mapRequestToEntity(DestinationRequest req, Destination dest) {
        dest.setName(req.getName());
        dest.setCategory(req.getCategory());
        dest.setSubDistrict(req.getSubDistrict());
        dest.setDescription(req.getDescription());
        dest.setShortDescription(req.getShortDescription());
        dest.setCulturalSignificance(req.getCulturalSignificance());
        dest.setTicketPrice(req.getTicketPrice());
        dest.setEstimatedDurationHours(req.getEstimatedDurationHours());
        dest.setOpeningHours(req.getOpeningHours() != null ? req.getOpeningHours() : "08:00 - 18:00 WIB");
        dest.setLatitude(req.getLatitude());
        dest.setLongitude(req.getLongitude());
        dest.setImageUrl(req.getImageUrl());
        dest.setGalleryUrls(req.getGalleryUrls());
        dest.setFacilities(req.getFacilities());
        dest.setIsFeatured(req.getIsFeatured() != null ? req.getIsFeatured() : false);
    }

    private String ensureUniqueSlug(String baseSlug, Long excludeId) {
        String slug = baseSlug;
        int counter = 1;
        while (true) {
            var existing = destinationRepository.findBySlug(slug);
            if (existing.isEmpty() || (excludeId != null && existing.get().getId().equals(excludeId))) {
                return slug;
            }
            slug = baseSlug + "-" + counter++;
        }
    }
}
