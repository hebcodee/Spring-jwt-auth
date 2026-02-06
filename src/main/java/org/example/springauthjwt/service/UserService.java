package org.example.springauthjwt.service;

import org.example.springauthjwt.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUser(Long id);
    List<User>getUsers();
}
