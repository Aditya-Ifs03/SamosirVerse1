package com.samosirverse.dto;

public class DashboardStatsResponse {
    private long totalDestinations;
    private long totalCreators;
    private long totalProducts;
    private long totalStories;
    private long totalExperiences;

    public DashboardStatsResponse() {}

    public DashboardStatsResponse(long totalDestinations, long totalCreators, long totalProducts, long totalStories, long totalExperiences) {
        this.totalDestinations = totalDestinations;
        this.totalCreators = totalCreators;
        this.totalProducts = totalProducts;
        this.totalStories = totalStories;
        this.totalExperiences = totalExperiences;
    }

    public long getTotalDestinations() { return totalDestinations; }
    public void setTotalDestinations(long totalDestinations) { this.totalDestinations = totalDestinations; }

    public long getTotalCreators() { return totalCreators; }
    public void setTotalCreators(long totalCreators) { this.totalCreators = totalCreators; }

    public long getTotalProducts() { return totalProducts; }
    public void setTotalProducts(long totalProducts) { this.totalProducts = totalProducts; }

    public long getTotalStories() { return totalStories; }
    public void setTotalStories(long totalStories) { this.totalStories = totalStories; }

    public long getTotalExperiences() { return totalExperiences; }
    public void setTotalExperiences(long totalExperiences) { this.totalExperiences = totalExperiences; }
}
