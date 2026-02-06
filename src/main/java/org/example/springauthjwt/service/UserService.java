package org.example.springauthjwt.service;

import org.example.springauthjwt.controller.dto.LoginRequest;
import org.example.springauthjwt.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUser(Long id);
    List<User>getUsers();
    boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder, User user);
}
