package com.frameworksbackend.trabajofinal.controller;

import com.frameworksbackend.trabajofinal.dto.RegisterRequest;
import com.frameworksbackend.trabajofinal.dto.LoginRequest;
import com.frameworksbackend.trabajofinal.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        userService.registerUser(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                registerRequest.getPassword()
        );
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String authenticationResult = userService.authenticateUser(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        if (authenticationResult.equals("Login successful")) {
            return ResponseEntity.ok(authenticationResult);
        } else {
            return ResponseEntity.status(401).body(authenticationResult);
        }
    }
}
