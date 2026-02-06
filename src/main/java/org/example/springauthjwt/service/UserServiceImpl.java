package org.example.springauthjwt.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springauthjwt.domain.User;
import org.example.springauthjwt.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;

    @Override
    public User saveUser(User user) {
        log.info("New User Save Success To The Database");
        return userRepo.save(user);
    }

    @Override
    public User getUser(Long id) {
        log.info("Fetching user {}", id);
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }
}
