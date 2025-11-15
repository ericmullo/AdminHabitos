package com.proyectohabitos.backend.service;

import com.proyectohabitos.backend.model.HabitRecord;
import com.proyectohabitos.backend.repository.HabitRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Servicio de lectura agregada para el Dashboard.
 *
 * - Calcula porcentajes semanales por hábito (a nivel familiar)
 * - Calcula un score semanal familiar
 * - (Opcional) Calcula porcentajes por miembro para un hábito concreto
 *
 * La idea es que TODO sea sólo de lectura, sin modificar datos.
 */
@Service
public class DashboardService {

    private final HabitRecordRepository recordRepo;

    public DashboardService(HabitRecordRepository recordRepo) {
        this.recordRepo = recordRepo;
    }

    /**
     * Obtiene todos los registros de la última semana (7 días) para una familia.
     * Filtra en memoria usando findAll() para no depender de métodos especiales
     * en el repositorio.
     */
    private List<HabitRecord> obtenerRegistrosUltimaSemana(Long familyId) {
        LocalDate hoy = LocalDate.now();
        LocalDate hace7Dias = hoy.minusDays(6); // rango [hace7Dias, hoy]

        return recordRepo.findAll()
                .stream()
                .filter(r -> r.getMember() != null
                        && r.getMember().getFamily() != null
                        && Objects.equals(r.getMember().getFamily().getId(), familyId)
                        && r.getFecha() != null
                        && !r.getFecha().isBefore(hace7Dias)
                        && !r.getFecha().isAfter(hoy))
                .collect(Collectors.toList());
    }

    /**
     * Calcula, para cada hábito, el porcentaje de cumplimiento semanal
     * para la familia indicada.
     *
     * Regla:
     *   metaSemanaHabitofamilia = metaDiaria * 7 * NºMiembrosQueRegistraronEseHábito
     *   porcentaje = (totalRealizado / metaSemanaHabitofamilia) * 100
     */
    public Map<String, Double> porcentajeSemanalPorHabito(Long familyId) {
        List<HabitRecord> registros = obtenerRegistrosUltimaSemana(familyId);

        if (registros.isEmpty()) {
            return Collections.emptyMap();
        }

        // Agrupamos por nombre de hábito
        Map<String, List<HabitRecord>> porHabito = registros.stream()
                .filter(r -> r.getHabit() != null && r.getHabit().getNombre() != null)
                .collect(Collectors.groupingBy(r -> r.getHabit().getNombre()));

        Map<String, Double> resultado = new LinkedHashMap<>();

        porHabito.forEach((nombreHabit, registrosHabit) -> {
            // Total realizado en la semana para ese hábito (suma de cantidadRealizada)
            double totalRealizado = registrosHabit.stream()
                    .mapToDouble(r -> r.getCantidadRealizada() != null ? r.getCantidadRealizada() : 0.0)
                    .sum();

            // set de miembros distintos que han registrado este hábito
            Set<Long> miembrosDistintos = registrosHabit.stream()
                    .filter(r -> r.getMember() != null)
                    .map(r -> r.getMember().getId())
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            // meta diaria tomada del primer registro (asumimos misma meta para todos)
            double metaDiaria = 0.0;
            if (!registrosHabit.isEmpty() && registrosHabit.get(0).getHabit() != null
                    && registrosHabit.get(0).getHabit().getMetaDiaria() != null) {
                metaDiaria = registrosHabit.get(0).getHabit().getMetaDiaria();
            }

            // meta semanal familiar para ese hábito
            double metaSemanalFamilia = metaDiaria * 7 * miembrosDistintos.size();

            double porcentaje = 0.0;
            if (metaSemanalFamilia > 0) {
                porcentaje = (totalRealizado / metaSemanalFamilia) * 100.0;
            }

            // limitamos a 0–120% por si se pasan de la meta
            porcentaje = Math.max(0, Math.min(porcentaje, 120));

            resultado.put(nombreHabit, porcentaje);
        });

        return resultado;
    }

    /**
     * Calcula un score general familiar semanal, promediando
     * los porcentajes de todos los hábitos.
     */
    public double porcentajeFamiliarSemanal(Long familyId) {
        Map<String, Double> porHabito = porcentajeSemanalPorHabito(familyId);

        if (porHabito.isEmpty()) {
            return 0.0;
        }

        return porHabito.values().stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    /**
     * (Opcional) Porcentaje semanal por miembro para un hábito concreto.
     * Útil si luego quieres un gráfico tipo:
     * Dormir -> Eric 100%, Ana 80%, Josue 60% ...
     */
    public Map<String, Double> porcentajeSemanalPorMiembroYHabito(Long familyId, Long habitId) {
        List<HabitRecord> registros = obtenerRegistrosUltimaSemana(familyId).stream()
                .filter(r -> r.getHabit() != null && Objects.equals(r.getHabit().getId(), habitId))
                .collect(Collectors.toList());

        if (registros.isEmpty()) {
            return Collections.emptyMap();
        }

        // Agrupamos por miembro
        Map<String, List<HabitRecord>> porMiembro = registros.stream()
                .filter(r -> r.getMember() != null && r.getMember().getNombres() != null)
                .collect(Collectors.groupingBy(r -> r.getMember().getNombres()));

        Map<String, Double> resultado = new LinkedHashMap<>();

        // meta diaria del hábito
        double metaDiaria = 0.0;
        if (!registros.isEmpty() && registros.get(0).getHabit() != null
                && registros.get(0).getHabit().getMetaDiaria() != null) {
            metaDiaria = registros.get(0).getHabit().getMetaDiaria();
        }
        double metaSemanal = metaDiaria * 7; // por persona

        porMiembro.forEach((nombreMiembro, registrosMiembro) -> {
            double totalRealizado = registrosMiembro.stream()
                    .mapToDouble(r -> r.getCantidadRealizada() != null ? r.getCantidadRealizada() : 0.0)
                    .sum();

            double porcentaje = 0.0;
            if (metaSemanal > 0) {
                porcentaje = (totalRealizado / metaSemanal) * 100.0;
            }

            porcentaje = Math.max(0, Math.min(porcentaje, 120));
            resultado.put(nombreMiembro, porcentaje);
        });

        return resultado;
    }
}
