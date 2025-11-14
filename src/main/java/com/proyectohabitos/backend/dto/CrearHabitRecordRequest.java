package com.proyectohabitos.backend.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CrearHabitRecordRequest {

    @NotNull
    private Long memberId;

    @NotNull
    private Long habitId;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private Double cantidadRealizada;

    // ==== GETTERS Y SETTERS ====

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getHabitId() {
        return habitId;
    }

    public void setHabitId(Long habitId) {
        this.habitId = habitId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getCantidadRealizada() {
        return cantidadRealizada;
    }

    public void setCantidadRealizada(Double cantidadRealizada) {
        this.cantidadRealizada = cantidadRealizada;
    }
}
