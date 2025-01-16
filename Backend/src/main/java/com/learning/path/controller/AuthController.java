package com.learning.path.controller;

import com.learning.path.dto.AuthRequest;
import com.learning.path.dto.AuthResponse;
import com.learning.path.dto.ErrorResponse;
import com.learning.path.dto.RegisterRequest;
import com.learning.path.model.User;
import com.learning.path.model.UserRole;
import com.learning.path.security.JwtUtil;
import com.learning.path.service.AuthService;
import com.learning.path.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // Remove the constructor since we're using field injection

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        // Check if username already exists
        if (userService.existsByUsername(request.getUsername())) {
            System.out.println("Register endpoint hit");
            System.out.println("Request body: " + request);
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Username already exists");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        // Check if email already exists
        if (userService.existsByEmail(request.getEmail())) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Email already exists");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        // Rest of your code remains the same
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(UserRole.USER);

        User savedUser = userService.createUser(user);

        String token = jwtUtil.generateToken(savedUser.getUsername(), savedUser.getRole().toString());
        return ResponseEntity.ok(new AuthResponse(token, savedUser.getUsername(), savedUser.getRole().toString()));
    }

}