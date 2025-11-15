// src/main/java/com/proyectohabitos/backend/service/AuthService.java
package com.proyectohabitos.backend.service;

import com.proyectohabitos.backend.dto.LoginRequest;
import com.proyectohabitos.backend.dto.LoginResponse;
import com.proyectohabitos.backend.dto.RegisterRequest;
import com.proyectohabitos.backend.exception.BadRequestException;
import com.proyectohabitos.backend.model.AppUser;
import com.proyectohabitos.backend.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AppUserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AppUserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse register(RegisterRequest req) {
        String emailNormalizado = req.email().trim().toLowerCase();

        // 1. Validar si ya existe una cuenta con ese correo
        if (userRepo.existsByEmail(emailNormalizado)) {
            throw new BadRequestException("Ya existe una cuenta registrada con ese correo.");
        }

        // 2. Crear el usuario
        AppUser user = new AppUser();
        user.setNombre(req.nombre().trim());
        user.setEmail(emailNormalizado);

        // 3. Guardar contraseña encriptada
        String hash = passwordEncoder.encode(req.password());
        user.setPasswordHash(hash);

        userRepo.save(user);

        // 4. Devolver datos básicos (sin contraseña)
        return new LoginResponse(user.getId(), user.getNombre(), user.getEmail());
    }

    public LoginResponse login(LoginRequest req) {
        String emailNormalizado = req.email().trim().toLowerCase();

        AppUser user = userRepo.findByEmail(emailNormalizado)
                .orElseThrow(() -> new BadRequestException("Correo o contraseña incorrectos."));

        // Comparar password en texto con el hash guardado
        boolean passwordOk = passwordEncoder.matches(req.password(), user.getPasswordHash());

        if (!passwordOk) {
            throw new BadRequestException("Correo o contraseña incorrectos.");
        }

        return new LoginResponse(user.getId(), user.getNombre(), user.getEmail());
    }
}
