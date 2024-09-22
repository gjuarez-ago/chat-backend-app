package com.example.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200") // Reemplaza con el origen permitido
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestParam String username, @RequestParam String password) {
        if (userRepository.findByUsername(username) != null) {
            return ResponseEntity.badRequest().body(null);  // Ya existe un usuario con ese nombre
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getUser() {
        List<User> user = userRepository.findAll();
        return ResponseEntity.ok(user);
    }

}
