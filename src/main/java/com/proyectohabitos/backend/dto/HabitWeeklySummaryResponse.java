// src/main/java/com/proyectohabitos/backend/dto/HabitWeeklySummaryResponse.java
package com.proyectohabitos.backend.dto;

public class HabitWeeklySummaryResponse {

    private String habitName;
    private double percentage;

    public HabitWeeklySummaryResponse() {
    }

    public HabitWeeklySummaryResponse(String habitName, double percentage) {
        this.habitName = habitName;
        this.percentage = percentage;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
