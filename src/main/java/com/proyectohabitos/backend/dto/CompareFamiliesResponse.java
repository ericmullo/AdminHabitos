package com.proyectohabitos.backend.dto;

public class CompareFamiliesResponse {

    private FamilyWeeklyDashboardResponse family1;
    private FamilyWeeklyDashboardResponse family2;

    public CompareFamiliesResponse() {
    }

    public CompareFamiliesResponse(FamilyWeeklyDashboardResponse family1,
                                   FamilyWeeklyDashboardResponse family2) {
        this.family1 = family1;
        this.family2 = family2;
    }

    public FamilyWeeklyDashboardResponse getFamily1() {
        return family1;
    }

    public void setFamily1(FamilyWeeklyDashboardResponse family1) {
        this.family1 = family1;
    }

    public FamilyWeeklyDashboardResponse getFamily2() {
        return family2;
    }

    public void setFamily2(FamilyWeeklyDashboardResponse family2) {
        this.family2 = family2;
    }
}
