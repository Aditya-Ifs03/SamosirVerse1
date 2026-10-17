package com.samosirverse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "destinations")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String slug;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(name = "sub_district", nullable = false, length = 50)
    private String subDistrict;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "short_description", nullable = false, length = 255)
    private String shortDescription;

    @Column(name = "cultural_significance", columnDefinition = "TEXT")
    private String culturalSignificance;

    @Column(name = "ticket_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal ticketPrice = BigDecimal.ZERO;

    @Column(name = "estimated_duration_hours", nullable = false, precision = 3, scale = 1)
    private BigDecimal estimatedDurationHours = new BigDecimal("1.5");

    @Column(name = "opening_hours", nullable = false, length = 100)
    private String openingHours = "08:00 - 18:00 WIB";

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl;

    @Column(name = "gallery_urls", columnDefinition = "TEXT")
    private String galleryUrls;

    @Column(columnDefinition = "TEXT")
    private String facilities;

    @Column(name = "is_featured", nullable = false)
    private Boolean isFeatured = false;

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal rating = new BigDecimal("4.80");

    @Column(name = "review_count", nullable = false)
    private Integer reviewCount = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Destination() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.rating == null) this.rating = new BigDecimal("4.80");
        if (this.reviewCount == null) this.reviewCount = 0;
        if (this.ticketPrice == null) this.ticketPrice = BigDecimal.ZERO;
        if (this.isFeatured == null) this.isFeatured = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getSubDistrict() { return subDistrict; }
    public void setSubDistrict(String subDistrict) { this.subDistrict = subDistrict; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

    public String getCulturalSignificance() { return culturalSignificance; }
    public void setCulturalSignificance(String culturalSignificance) { this.culturalSignificance = culturalSignificance; }

    public BigDecimal getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(BigDecimal ticketPrice) { this.ticketPrice = ticketPrice; }

    public BigDecimal getEstimatedDurationHours() { return estimatedDurationHours; }
    public void setEstimatedDurationHours(BigDecimal estimatedDurationHours) { this.estimatedDurationHours = estimatedDurationHours; }

    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getGalleryUrls() { return galleryUrls; }
    public void setGalleryUrls(String galleryUrls) { this.galleryUrls = galleryUrls; }

    public String getFacilities() { return facilities; }
    public void setFacilities(String facilities) { this.facilities = facilities; }

    public Boolean getIsFeatured() { return isFeatured; }
    public void setIsFeatured(Boolean isFeatured) { this.isFeatured = isFeatured; }

    public BigDecimal getRating() { return rating; }
    public void setRating(BigDecimal rating) { this.rating = rating; }

    public Integer getReviewCount() { return reviewCount; }
    public void setReviewCount(Integer reviewCount) { this.reviewCount = reviewCount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
