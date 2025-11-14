package com.proyectohabitos.backend.controller;

import com.proyectohabitos.backend.model.Family;
import com.proyectohabitos.backend.repository.FamilyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/families")
@CrossOrigin(origins = "http://localhost:5173")
public class FamilyController {

    private final FamilyRepository familyRepository;

    public FamilyController(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    @GetMapping
    public List<Family> listar() {
        return familyRepository.findAll();
    }

    @PostMapping
    public Family crear(@RequestBody Family family) {
        return familyRepository.save(family);
    }
}
