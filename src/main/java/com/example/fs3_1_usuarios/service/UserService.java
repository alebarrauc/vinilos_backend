package com.example.fs3_1_usuarios.service;

import com.example.fs3_1_usuarios.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);

    // Nuevo método para buscar un usuario por username
    Optional<User> findByUsername(String username);
}
