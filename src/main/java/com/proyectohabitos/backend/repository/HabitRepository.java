package com.proyectohabitos.backend.repository;

import com.proyectohabitos.backend.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit, Long> {
}
