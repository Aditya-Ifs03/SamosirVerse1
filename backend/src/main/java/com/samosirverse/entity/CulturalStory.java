package com.samosirverse.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cultural_stories")
public class CulturalStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, unique = true, length = 200)
    private String slug;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(nullable = false, length = 300)
    private String summary;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "batak_quote", length = 300)
    private String batakQuote;

    @Column(name = "quote_meaning", length = 300)
    private String quoteMeaning;

    @Column(name = "reading_time_minutes", nullable = false)
    private Integer readingTimeMinutes = 5;

    @Column(name = "cover_image_url", nullable = false, length = 500)
    private String coverImageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "related_destination_id")
    private Destination relatedDestination;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public CulturalStory() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.readingTimeMinutes == null) this.readingTimeMinutes = 5;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getBatakQuote() { return batakQuote; }
    public void setBatakQuote(String batakQuote) { this.batakQuote = batakQuote; }

    public String getQuoteMeaning() { return quoteMeaning; }
    public void setQuoteMeaning(String quoteMeaning) { this.quoteMeaning = quoteMeaning; }

    public Integer getReadingTimeMinutes() { return readingTimeMinutes; }
    public void setReadingTimeMinutes(Integer readingTimeMinutes) { this.readingTimeMinutes = readingTimeMinutes; }

    public String getCoverImageUrl() { return coverImageUrl; }
    public void setCoverImageUrl(String coverImageUrl) { this.coverImageUrl = coverImageUrl; }

    public Destination getRelatedDestination() { return relatedDestination; }
    public void setRelatedDestination(Destination relatedDestination) { this.relatedDestination = relatedDestination; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
