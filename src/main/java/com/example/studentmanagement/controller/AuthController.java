package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.AuthRequest;
import com.example.studentmanagement.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        // In a real-world application, authenticate against a user store
        if ("admin".equals(authRequest.getUsername()) && "admin".equals(authRequest.getPassword())) {
            String token = jwtUtil.generateToken(authRequest.getUsername(), "admin");
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }
}
