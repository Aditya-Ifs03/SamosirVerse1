package com.samosirverse.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ExperienceRequest {

    @NotBlank(message = "Judul agenda/pengalaman wajib diisi")
    @Size(max = 200, message = "Judul maksimal 200 karakter")
    private String title;

    @NotBlank(message = "Tipe pengalaman wajib dipilih")
    private String type;

    @NotBlank(message = "Lokasi pelaksanaan wajib diisi")
    private String location;

    @NotBlank(message = "Jadwal/tanggal acara wajib diisi")
    private String eventDate;

    @NotBlank(message = "Durasi kegiatan wajib diisi")
    private String duration;

    @NotNull(message = "Biaya kegiatan wajib diisi")
    @DecimalMin(value = "0.0", message = "Biaya kegiatan tidak boleh negatif")
    private BigDecimal price;

    @NotBlank(message = "Deskripsi pengalaman wajib diisi")
    private String description;

    @NotBlank(message = "Nama penyelenggara/komunitas wajib diisi")
    private String organizerName;

    @NotBlank(message = "Nomor WhatsApp pemesanan wajib diisi")
    @Pattern(
        regexp = "^(\\+62|62|0)8[1-9][0-9]{7,11}$",
        message = "Nomor WhatsApp pemesanan harus format Indonesia valid"
    )
    private String whatsappBooking;

    @NotBlank(message = "URL foto kegiatan wajib diisi")
    private String imageUrl;

    private Integer maxParticipants = 20;

    public ExperienceRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getOrganizerName() { return organizerName; }
    public void setOrganizerName(String organizerName) { this.organizerName = organizerName; }

    public String getWhatsappBooking() { return whatsappBooking; }
    public void setWhatsappBooking(String whatsappBooking) { this.whatsappBooking = whatsappBooking; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Integer getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(Integer maxParticipants) { this.maxParticipants = maxParticipants; }
}
