// src/main/java/com/proyectohabitos/backend/controller/DashboardController.java
package com.proyectohabitos.backend.controller;

import com.proyectohabitos.backend.dto.FamilyWeeklyDashboardResponse;
import com.proyectohabitos.backend.dto.HabitWeeklySummaryResponse;
import com.proyectohabitos.backend.model.Family;
import com.proyectohabitos.backend.repository.FamilyRepository;
import com.proyectohabitos.backend.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")          // 👈 IMPORTANTE: empieza en /api
@CrossOrigin(origins = "*")                // 👈 deja todo abierto mientras pruebas
public class DashboardController {

    private final DashboardService dashboardService;
    private final FamilyRepository familyRepository;

    public DashboardController(DashboardService dashboardService,
                               FamilyRepository familyRepository) {
        this.dashboardService = dashboardService;
        this.familyRepository = familyRepository;
    }

    @GetMapping("/family/{familyId}/weekly")
    public FamilyWeeklyDashboardResponse getWeeklyDashboard(@PathVariable Long familyId) {

        Map<String, Double> porHabito =
                dashboardService.porcentajeSemanalPorHabito(familyId);

        double overall = dashboardService.porcentajeFamiliarSemanal(familyId);

        Family family = familyRepository.findById(familyId).orElse(null);
        String familyName = (family != null && family.getNombre() != null)
                ? family.getNombre()
                : "Familia " + familyId;

        LocalDate hoy = LocalDate.now();
        LocalDate hace7Dias = hoy.minusDays(6);

        List<HabitWeeklySummaryResponse> habits = porHabito.entrySet()
                .stream()
                .map(e -> new HabitWeeklySummaryResponse(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        return new FamilyWeeklyDashboardResponse(
                familyId,
                familyName,
                hace7Dias.toString(),
                hoy.toString(),
                overall,
                habits
        );
    }
}
