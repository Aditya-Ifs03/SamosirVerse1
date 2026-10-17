package com.samosirverse.dto;

import jakarta.validation.constraints.*;

public class CulturalStoryRequest {

    @NotBlank(message = "Judul cerita budaya wajib diisi")
    @Size(max = 200, message = "Judul cerita maksimal 200 karakter")
    private String title;

    @NotBlank(message = "Kategori cerita wajib dipilih")
    private String category;

    @NotBlank(message = "Ringkasan cerita wajib diisi")
    @Size(max = 300, message = "Ringkasan cerita maksimal 300 karakter")
    private String summary;

    @NotBlank(message = "Konten lengkap artikel budaya wajib diisi")
    private String content;

    private String batakQuote;
    private String quoteMeaning;

    @Min(value = 1, message = "Waktu baca minimal 1 menit")
    private Integer readingTimeMinutes = 5;

    @NotBlank(message = "URL foto sampul artikel wajib diisi")
    private String coverImageUrl;

    private Long relatedDestinationId;

    public CulturalStoryRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

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

    public Long getRelatedDestinationId() { return relatedDestinationId; }
    public void setRelatedDestinationId(Long relatedDestinationId) { this.relatedDestinationId = relatedDestinationId; }
}
