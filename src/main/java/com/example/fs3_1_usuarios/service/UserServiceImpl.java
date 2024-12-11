package com.example.fs3_1_usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fs3_1_usuarios.model.User;
import com.example.fs3_1_usuarios.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id)
                .or(() -> {
                    throw new RuntimeException("User not found with id: " + id);
                });
    }

    @Override
    public User createUser(User user) {
        // Verificación para evitar duplicados en el email
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already in use: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    // Eliminar setNombre ya que no existe en la clase User
                    existingUser.setRole(user.getRole());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPassword(user.getPassword());
                    existingUser.setUsername(user.getUsername());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
