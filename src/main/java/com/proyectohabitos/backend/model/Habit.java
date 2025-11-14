package com.proyectohabitos.backend.model;

import jakarta.persistence.*;

@Entity
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String unidad; // horas, vasos, km…

    @Column(nullable = false)
    private Double metaDiaria;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Double getMetaDiaria() {
        return metaDiaria;
    }

    public void setMetaDiaria(Double metaDiaria) {
        this.metaDiaria = metaDiaria;
    }
}
