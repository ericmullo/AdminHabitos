// src/main/java/com/proyectohabitos/backend/controller/FamilyController.java
package com.proyectohabitos.backend.controller;

import com.proyectohabitos.backend.dto.CreateFamilyRequest;
import com.proyectohabitos.backend.dto.FamilyResponse;
import com.proyectohabitos.backend.service.FamilyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/familias")
@CrossOrigin(origins = "*") // ajusta si necesitas algo más estricto
public class FamilyController {

    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GetMapping
    public List<FamilyResponse> listar() {
        return familyService.listarFamilias();
    }

    @PostMapping
    public FamilyResponse crear(@Valid @RequestBody CreateFamilyRequest request) {
        return familyService.crearFamilia(request);
    }
}
