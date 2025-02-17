package com.frameworksbackend.trabajofinal.service;

import com.frameworksbackend.trabajofinal.model.User;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    User saveUser(User user);
    void deleteUser(Long id);
    void initializeAdminUser();
    User registerUser(String username, String email, String password);
    String authenticateUser(String email, String password);
}