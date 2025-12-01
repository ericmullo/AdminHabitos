package com.proyectohabitos.backend.repository;

import com.proyectohabitos.backend.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {

    boolean existsByNombreIgnoreCase(String nombre);
}