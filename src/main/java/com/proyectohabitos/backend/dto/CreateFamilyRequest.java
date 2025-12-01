// src/main/java/com/proyectohabitos/backend/dto/CreateFamilyRequest.java
package com.proyectohabitos.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateFamilyRequest {

    @NotBlank(message = "El nombre de la familia es obligatorio")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
