// src/main/java/com/proyectohabitos/backend/dto/FamilyResponse.java
package com.proyectohabitos.backend.dto;

public class FamilyResponse {
    private Long id;
    private String nombre;

    public FamilyResponse(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
