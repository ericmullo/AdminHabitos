// src/main/java/com/proyectohabitos/backend/dto/FamilyWeeklyDashboardResponse.java
package com.proyectohabitos.backend.dto;

import java.util.List;

public class FamilyWeeklyDashboardResponse {

    private Long familyId;
    private String familyName;
    private String startDate;       // ISO (yyyy-MM-dd)
    private String endDate;         // ISO
    private double overallPercentage;
    private List<HabitWeeklySummaryResponse> habits;

    public FamilyWeeklyDashboardResponse() {
    }

    public FamilyWeeklyDashboardResponse(Long familyId,
                                         String familyName,
                                         String startDate,
                                         String endDate,
                                         double overallPercentage,
                                         List<HabitWeeklySummaryResponse> habits) {
        this.familyId = familyId;
        this.familyName = familyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.overallPercentage = overallPercentage;
        this.habits = habits;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getOverallPercentage() {
        return overallPercentage;
    }

    public void setOverallPercentage(double overallPercentage) {
        this.overallPercentage = overallPercentage;
    }

    public List<HabitWeeklySummaryResponse> getHabits() {
        return habits;
    }

    public void setHabits(List<HabitWeeklySummaryResponse> habits) {
        this.habits = habits;
    }
}
