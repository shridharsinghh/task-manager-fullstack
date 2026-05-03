package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.dto.LoginRequest;
import com.taskmanager.taskmanager.dto.RegisterRequest;
import com.taskmanager.taskmanager.model.Role;
import com.taskmanager.taskmanager.model.User;
import com.taskmanager.taskmanager.repository.UserRepository;
import com.taskmanager.taskmanager.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder; // ✅ FIX

    // ================= REGISTER =================
    public Map<String, Object> register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // ✅ FIX
                .role(Role.MEMBER)
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());

        return Map.of(
                "message", "User registered successfully",
                "token", token,
                "email", user.getEmail(),   // 🔥 MUST
                "role", user.getRole().name() // 🔥 MUST
        );
    }

    // ================= LOGIN =================
    public Map<String, Object> login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return Map.of(
                "message", "Login successful",
                "token", token,
                "email", user.getEmail(),
                "role", user.getRole().name()
        );
    }
}