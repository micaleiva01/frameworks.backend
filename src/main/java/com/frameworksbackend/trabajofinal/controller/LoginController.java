package com.frameworksbackend.trabajofinal.controller;

import com.frameworksbackend.trabajofinal.model.User;
import com.frameworksbackend.trabajofinal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend access
public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public String login(@RequestBody User loginRequest, HttpSession session) {
        User authenticatedUser = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        session.setAttribute("user", authenticatedUser); // Store user in the session
        return "Login successful for user: " + authenticatedUser.getEmail();
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return "Logged out successfully.";
    }
}
