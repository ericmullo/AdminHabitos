package com.proyectohabitos.backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class HabitRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Member member;

    @ManyToOne(optional = false)
    private Habit habit;

    private LocalDate fecha;

    private Double cantidadRealizada;

    private Double porcentajeCumplimiento;

    // ==== GETTERS Y SETTERS ====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
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

    public Double getPorcentajeCumplimiento() {
        return porcentajeCumplimiento;
    }

    public void setPorcentajeCumplimiento(Double porcentajeCumplimiento) {
        this.porcentajeCumplimiento = porcentajeCumplimiento;
    }
}
