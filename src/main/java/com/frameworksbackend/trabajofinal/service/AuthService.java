package com.frameworksbackend.trabajofinal.service;

import com.frameworksbackend.trabajofinal.model.User;
import com.frameworksbackend.trabajofinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User authenticate(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        return user.orElse(null);
    }

    public String getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getAuthorities().toString();
        }
        return null;
    }
}
