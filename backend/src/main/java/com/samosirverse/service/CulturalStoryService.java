package com.samosirverse.service;

import com.samosirverse.dto.CulturalStoryRequest;
import com.samosirverse.entity.CulturalStory;
import com.samosirverse.entity.Destination;
import com.samosirverse.exception.ResourceNotFoundException;
import com.samosirverse.repository.CulturalStoryRepository;
import com.samosirverse.repository.DestinationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CulturalStoryService {

    private final CulturalStoryRepository storyRepository;
    private final DestinationRepository destinationRepository;

    public CulturalStoryService(CulturalStoryRepository storyRepository, DestinationRepository destinationRepository) {
        this.storyRepository = storyRepository;
        this.destinationRepository = destinationRepository;
    }

    @Transactional(readOnly = true)
    public List<CulturalStory> getAll(String category, String search) {
        return storyRepository.filterStories(category, search);
    }

    @Transactional(readOnly = true)
    public CulturalStory getById(Long id) {
        return storyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cerita budaya dengan ID " + id + " tidak ditemukan"));
    }

    @Transactional(readOnly = true)
    public CulturalStory getBySlug(String slug) {
        return storyRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Cerita budaya dengan slug '" + slug + "' tidak ditemukan"));
    }

    public CulturalStory create(CulturalStoryRequest request) {
        CulturalStory story = new CulturalStory();
        mapRequestToEntity(request, story);
        String baseSlug = SlugUtils.toSlug(request.getTitle());
        story.setSlug(ensureUniqueSlug(baseSlug, null));
        return storyRepository.save(story);
    }

    public CulturalStory update(Long id, CulturalStoryRequest request) {
        CulturalStory story = getById(id);
        mapRequestToEntity(request, story);
        String baseSlug = SlugUtils.toSlug(request.getTitle());
        story.setSlug(ensureUniqueSlug(baseSlug, id));
        return storyRepository.save(story);
    }

    public void delete(Long id) {
        CulturalStory story = getById(id);
        storyRepository.delete(story);
    }

    private void mapRequestToEntity(CulturalStoryRequest req, CulturalStory story) {
        story.setTitle(req.getTitle());
        story.setCategory(req.getCategory());
        story.setSummary(req.getSummary());
        story.setContent(req.getContent());
        story.setBatakQuote(req.getBatakQuote());
        story.setQuoteMeaning(req.getQuoteMeaning());
        story.setReadingTimeMinutes(req.getReadingTimeMinutes() != null ? req.getReadingTimeMinutes() : 5);
        story.setCoverImageUrl(req.getCoverImageUrl());

        if (req.getRelatedDestinationId() != null) {
            Destination dest = destinationRepository.findById(req.getRelatedDestinationId()).orElse(null);
            story.setRelatedDestination(dest);
        } else {
            story.setRelatedDestination(null);
        }
    }

    private String ensureUniqueSlug(String baseSlug, Long excludeId) {
        String slug = baseSlug;
        int counter = 1;
        while (true) {
            var existing = storyRepository.findBySlug(slug);
            if (existing.isEmpty() || (excludeId != null && existing.get().getId().equals(excludeId))) {
                return slug;
            }
            slug = baseSlug + "-" + counter++;
        }
    }
}
