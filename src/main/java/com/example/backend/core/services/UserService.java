package com.example.backend.core.services;

import com.example.backend.core.models.User;
import com.example.backend.core.repositorys.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {

        return new ArrayList<>(userRepository.findAll());
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(new User());
    }

    public User getUserFromAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (User) authentication.getPrincipal();
    }

    public User getUserFromEmail(String email) {
        return userRepository.findByEmail(email).orElse(new User());
    }

    public List<User> getUserByName(String name){
        return userRepository.findByName(name).orElse(List.of());
    }

    public void changeEmailVerificationStatus(User user, boolean status) {
        user.setEmailIsVerified(status);
        save(user);
    }

    public void checkIfEmailIsVerified(User user) {
        if (!user.isEmailIsVerified()) {
            throw new RuntimeException("Email is not verified");
        }
    }
}