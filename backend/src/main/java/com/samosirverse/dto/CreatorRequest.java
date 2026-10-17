package com.samosirverse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreatorRequest {

    @NotBlank(message = "Nama kreator/pengrajin wajib diisi")
    @Size(max = 100, message = "Nama kreator maksimal 100 karakter")
    private String name;

    @NotBlank(message = "Keahlian/jenis kerajinan wajib diisi")
    private String craftType;

    @NotBlank(message = "Desa/Kelurahan di Samosir wajib diisi")
    private String village;

    @NotBlank(message = "Biografi singkat wajib diisi")
    private String bio;

    @NotBlank(message = "Nomor WhatsApp wajib diisi")
    @Pattern(
        regexp = "^(\\+62|62|0)8[1-9][0-9]{7,11}$",
        message = "Nomor WhatsApp harus format Indonesia valid (contoh: +628123456789 atau 081234567890)"
    )
    private String whatsappNumber;

    private Boolean isWhatsappVerified = true;

    @NotBlank(message = "URL foto profil kreator wajib diisi")
    private String profileImageUrl;

    private Integer yearsOfExperience = 5;
    private String awards;

    public CreatorRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

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
}
