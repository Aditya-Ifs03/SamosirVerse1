package com.samosirverse.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TripPlanResponse {

    private String planTitle;
    private String summary;
    private Integer durationDays;
    private String travelStyle;
    private String budgetCategory;
    private String groupType;
    private BigDecimal totalEstimatedCost;
    private String costBreakdown;
    private List<DayPlan> days = new ArrayList<>();
    private List<String> culturalEtiquetteTips = new ArrayList<>();

    public static class DayPlan {
        private int dayNumber;
        private String theme;
        private List<ActivityItem> activities = new ArrayList<>();

        public DayPlan() {}
        public DayPlan(int dayNumber, String theme) {
            this.dayNumber = dayNumber;
            this.theme = theme;
        }

        public int getDayNumber() { return dayNumber; }
        public void setDayNumber(int dayNumber) { this.dayNumber = dayNumber; }

        public String getTheme() { return theme; }
        public void setTheme(String theme) { this.theme = theme; }

        public List<ActivityItem> getActivities() { return activities; }
        public void setActivities(List<ActivityItem> activities) { this.activities = activities; }
    }

    public static class ActivityItem {
        private String timeSlot;
        private String period; // PAGI, SIANG, SORE, MALAM
        private String title;
        private String location;
        private String description;
        private BigDecimal estimatedCost;
        private String tips;
        private Long destinationId;

        public ActivityItem() {}

        public ActivityItem(String timeSlot, String period, String title, String location, String description, BigDecimal estimatedCost, String tips, Long destinationId) {
            this.timeSlot = timeSlot;
            this.period = period;
            this.title = title;
            this.location = location;
            this.description = description;
            this.estimatedCost = estimatedCost;
            this.tips = tips;
            this.destinationId = destinationId;
        }

        public String getTimeSlot() { return timeSlot; }
        public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }

        public String getPeriod() { return period; }
        public void setPeriod(String period) { this.period = period; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public BigDecimal getEstimatedCost() { return estimatedCost; }
        public void setEstimatedCost(BigDecimal estimatedCost) { this.estimatedCost = estimatedCost; }

        public String getTips() { return tips; }
        public void setTips(String tips) { this.tips = tips; }

        public Long getDestinationId() { return destinationId; }
        public void setDestinationId(Long destinationId) { this.destinationId = destinationId; }
    }

    public TripPlanResponse() {}

    public String getPlanTitle() { return planTitle; }
    public void setPlanTitle(String planTitle) { this.planTitle = planTitle; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public Integer getDurationDays() { return durationDays; }
    public void setDurationDays(Integer durationDays) { this.durationDays = durationDays; }

    public String getTravelStyle() { return travelStyle; }
    public void setTravelStyle(String travelStyle) { this.travelStyle = travelStyle; }

    public String getBudgetCategory() { return budgetCategory; }
    public void setBudgetCategory(String budgetCategory) { this.budgetCategory = budgetCategory; }

    public String getGroupType() { return groupType; }
    public void setGroupType(String groupType) { this.groupType = groupType; }

    public BigDecimal getTotalEstimatedCost() { return totalEstimatedCost; }
    public void setTotalEstimatedCost(BigDecimal totalEstimatedCost) { this.totalEstimatedCost = totalEstimatedCost; }

    public String getCostBreakdown() { return costBreakdown; }
    public void setCostBreakdown(String costBreakdown) { this.costBreakdown = costBreakdown; }

    public List<DayPlan> getDays() { return days; }
    public void setDays(List<DayPlan> days) { this.days = days; }

    public List<String> getCulturalEtiquetteTips() { return culturalEtiquetteTips; }
    public void setCulturalEtiquetteTips(List<String> culturalEtiquetteTips) { this.culturalEtiquetteTips = culturalEtiquetteTips; }
}
