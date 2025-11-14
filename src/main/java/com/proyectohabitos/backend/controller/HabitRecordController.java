package com.proyectohabitos.backend.controller;

import com.proyectohabitos.backend.dto.CrearHabitRecordRequest;
import com.proyectohabitos.backend.model.HabitRecord;
import com.proyectohabitos.backend.service.HabitRecordService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@CrossOrigin(origins = "http://localhost:5173")
public class HabitRecordController {

    private final HabitRecordService service;

    public HabitRecordController(HabitRecordService service) {
        this.service = service;
    }

    @PostMapping
    public HabitRecord crear(@RequestBody @Valid CrearHabitRecordRequest req) {
        return service.crear(req);
    }

    @GetMapping("/by-family/{familyId}")
    public List<HabitRecord> listar(@PathVariable Long familyId) {
        return service.listarPorFamilia(familyId);
    }
}
