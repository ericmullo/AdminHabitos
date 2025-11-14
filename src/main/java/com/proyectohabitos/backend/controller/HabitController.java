package com.proyectohabitos.backend.controller;

import com.proyectohabitos.backend.model.Habit;
import com.proyectohabitos.backend.repository.HabitRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@CrossOrigin(origins = "http://localhost:5173")
public class HabitController {

    private final HabitRepository habitRepository;

    public HabitController(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @GetMapping
    public List<Habit> listar() {
        return habitRepository.findAll();
    }

    @PostMapping
    public Habit crear(@RequestBody Habit habit) {
        return habitRepository.save(habit);
    }
}
