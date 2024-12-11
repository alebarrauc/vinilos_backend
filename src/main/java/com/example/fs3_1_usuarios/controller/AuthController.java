package com.example.fs3_1_usuarios.controller;

import com.example.fs3_1_usuarios.model.LoginRequest;
import com.example.fs3_1_usuarios.model.User;
import com.example.fs3_1_usuarios.service.UserService;
import java.util.Map;
import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") // Ruta base para autenticación
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login") // Endpoint para login
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Busca al usuario en la base de datos por username
        User user = userService.findByUsername(loginRequest.getUsername())
                .orElse(null);

        // Verifica si el usuario existe y la contraseña coincide
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }

        // Crear un objeto de respuesta con el rol del usuario
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Inicio de sesión exitoso");
            response.put("role", user.getRole()); // Incluye el rol en la respuesta

        // Respuesta exitosa
       // return ResponseEntity.ok("Inicio de sesión exitoso");
        return ResponseEntity.ok(response);
        
    }
}
