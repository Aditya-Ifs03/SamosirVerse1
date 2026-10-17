package com.samosirverse.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "creators")
public class Creator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String slug;

    @Column(name = "craft_type", nullable = false, length = 100)
    private String craftType;

    @Column(nullable = false, length = 100)
    private String village;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String bio;

    @Column(name = "whatsapp_number", nullable = false, length = 30)
    private String whatsappNumber;

    @Column(name = "is_whatsapp_verified", nullable = false)
    private Boolean isWhatsappVerified = true;

    @Column(name = "profile_image_url", nullable = false, length = 500)
    private String profileImageUrl;

    @Column(name = "years_of_experience", nullable = false)
    private Integer yearsOfExperience = 5;

    @Column(columnDefinition = "TEXT")
    private String awards;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Creator() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.isWhatsappVerified == null) this.isWhatsappVerified = true;
        if (this.yearsOfExperience == null) this.yearsOfExperience = 5;
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

    public String getCraftType() { return craftType; }
    public void setCraftType(String craftType) { this.craftType = craftType; }

    public String getVillage() { return village; }
    public void setVillage(String village) { this.village = village; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getWhatsappNumber() { return whatsappNumber; }
    public void setWhatsappNumber(String whatsappNumber) { this.whatsappNumber = whatsappNumber; }

    public Boolean getIsWhatsappVerified() { return isWhatsappVerified; }
    public void setIsWhatsappVerified(Boolean isWhatsappVerified) { this.isWhatsappVerified = isWhatsappVerified; }

    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }

    public Integer getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(Integer yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    public String getAwards() { return awards; }
    public void setAwards(String awards) { this.awards = awards; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
