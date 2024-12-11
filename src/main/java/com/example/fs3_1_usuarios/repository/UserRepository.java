package com.example.fs3_1_usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.fs3_1_usuarios.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Método personalizado para verificar si un email ya existe
    boolean existsByEmail(String email);

    // Método para buscar un usuario por username
    Optional<User> findByUsername(String username);
}
