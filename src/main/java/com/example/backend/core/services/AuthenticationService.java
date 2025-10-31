package com.example.backend.core.services;

import com.example.backend.core.Dtos.LoginUserDto;
import com.example.backend.core.Dtos.RegisterUserDto;
import com.example.backend.core.models.User;
import com.example.backend.core.repositorys.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // In AuthenticationService.java

    public void signup(RegisterUserDto input) {
        try {
            input.setPassword(passwordEncoder.encode(input.getPassword()));
            userRepository.save(new User(input));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User authenticate(LoginUserDto input) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("WRONG_CREDENTIALS"));
    }
}