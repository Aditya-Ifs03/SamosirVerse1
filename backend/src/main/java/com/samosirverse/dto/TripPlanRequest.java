package com.samosirverse.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TripPlanRequest {

    @NotNull(message = "Durasi perjalanan wajib diisi (1, 2, atau 3 hari)")
    @Min(value = 1, message = "Durasi minimal 1 hari")
    @Max(value = 3, message = "Durasi maksimal 3 hari untuk paket cerdas")
    private Integer durationDays = 2;

    @NotBlank(message = "Gaya perjalanan wajib dipilih")
    private String travelStyle = "BUDAYA"; // BUDAYA, ALAM, SANTAI, KULINER_KREATIF

    @NotBlank(message = "Kategori anggaran wajib dipilih")
    private String budgetCategory = "STANDAR"; // HEMAT, STANDAR, PREMIUM

    private String groupType = "PASANGAN"; // SOLO, PASANGAN, KELUARGA, ROMBONGAN

    public TripPlanRequest() {}

    public Integer getDurationDays() { return durationDays; }
    public void setDurationDays(Integer durationDays) { this.durationDays = durationDays; }

    public String getTravelStyle() { return travelStyle; }
    public void setTravelStyle(String travelStyle) { this.travelStyle = travelStyle; }

    public String getBudgetCategory() { return budgetCategory; }
    public void setBudgetCategory(String budgetCategory) { this.budgetCategory = budgetCategory; }

    public String getGroupType() { return groupType; }
    public void setGroupType(String groupType) { this.groupType = groupType; }
}
