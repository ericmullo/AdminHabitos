// src/main/java/com/proyectohabitos/backend/repository/AppUserRepository.java
package com.proyectohabitos.backend.repository;

import com.proyectohabitos.backend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    boolean existsByEmail(String email);
}
