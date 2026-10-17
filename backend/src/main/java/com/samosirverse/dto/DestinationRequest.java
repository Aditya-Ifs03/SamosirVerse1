package com.samosirverse.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class DestinationRequest {

    @NotBlank(message = "Nama destinasi wajib diisi")
    @Size(max = 150, message = "Nama destinasi maksimal 150 karakter")
    private String name;

    @NotBlank(message = "Kategori wajib dipilih")
    private String category;

    @NotBlank(message = "Kecamatan di Samosir wajib diisi")
    private String subDistrict;

    @NotBlank(message = "Deskripsi lengkap wajib diisi")
    private String description;

    @NotBlank(message = "Deskripsi singkat wajib diisi")
    @Size(max = 255, message = "Deskripsi singkat maksimal 255 karakter")
    private String shortDescription;

    private String culturalSignificance;

    @NotNull(message = "Harga tiket masuk wajib diisi")
    @DecimalMin(value = "0.0", inclusive = true, message = "Harga tiket tidak boleh negatif")
    private BigDecimal ticketPrice;

    @NotNull(message = "Estimasi durasi kunjungan wajib diisi")
    @DecimalMin(value = "0.5", message = "Estimasi durasi minimal 0.5 jam")
    private BigDecimal estimatedDurationHours;

    private String openingHours = "08:00 - 18:00 WIB";

    @NotNull(message = "Latitude wajib diisi")
    private Double latitude;

    @NotNull(message = "Longitude wajib diisi")
    private Double longitude;

    @NotBlank(message = "URL foto utama wajib diisi")
    private String imageUrl;

    private String galleryUrls;
    private String facilities;
    private Boolean isFeatured = false;

    public DestinationRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

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
}
