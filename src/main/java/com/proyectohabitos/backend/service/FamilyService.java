// src/main/java/com/proyectohabitos/backend/service/FamilyService.java
package com.proyectohabitos.backend.service;

import com.proyectohabitos.backend.dto.CreateFamilyRequest;
import com.proyectohabitos.backend.dto.FamilyResponse;
import com.proyectohabitos.backend.exception.BadRequestException;
import com.proyectohabitos.backend.model.Family;
import com.proyectohabitos.backend.repository.FamilyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyService {

    private final FamilyRepository familyRepository;

    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public List<FamilyResponse> listarFamilias() {
        return familyRepository.findAll()
                .stream()
                .map(f -> new FamilyResponse(f.getId(), f.getNombre()))
                .toList();
    }

    public FamilyResponse crearFamilia(CreateFamilyRequest request) {
        String nombreNormalizado = normalizarNombreFamilia(request.getNombre());

        if (familyRepository.existsByNombreIgnoreCase(nombreNormalizado)) {
            throw new BadRequestException("Ya existe " + nombreNormalizado);
        }

        Family f = new Family();
        f.setNombre(nombreNormalizado);
        Family guardada = familyRepository.save(f);

        return new FamilyResponse(guardada.getId(), guardada.getNombre());
    }

    /**
     * Si el usuario escribe "Garcia", devuelve "Familia Garcia".
     * Si ya escribe "Familia Garcia", se respeta.
     */
    private String normalizarNombreFamilia(String nombreIngresado) {
        if (nombreIngresado == null) {
            throw new BadRequestException("El nombre de la familia es obligatorio");
        }

        String base = nombreIngresado.trim();

        if (base.isEmpty()) {
            throw new BadRequestException("El nombre de la familia no puede estar vacío");
        }

        String lower = base.toLowerCase();
        if (!lower.startsWith("familia ")) {
            base = "Familia " + base;
        }

        return base;
    }
}
