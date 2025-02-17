package com.frameworksbackend.trabajofinal.service;

import com.frameworksbackend.trabajofinal.model.User;
import com.frameworksbackend.trabajofinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void initializeAdminUser() {
    }

    @Override
    public User registerUser(String username, String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email is already in use!");
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public String authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return "User not found"; // Return a meaningful string instead of false
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Invalid email or password"; // Return message instead of boolean
        }

        return "Login successful"; // You can replace this with a JWT token later
    }

}
