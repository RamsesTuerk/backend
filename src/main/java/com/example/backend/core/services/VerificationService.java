package com.example.backend.core.services;

import com.example.backend.core.models.UserVerification;
import com.example.backend.core.repositorys.UserVerificationRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class VerificationService {

    private final UserVerificationRepository verificationRepository;
    //TODO überführe char und length application.properties
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 6;
    private static final SecureRandom random = new SecureRandom();

    public VerificationService(UserVerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    public void save(UserVerification userVerification) {
        verificationRepository.save(userVerification);
    }

    public String verifyCode(Long id, String code) {
        UserVerification userVerification = verificationRepository.findById(id).orElseThrow(() -> new RuntimeException("This Email is unknown"));
        if (!userVerification.getVerificationCode().equals(code)) {
            throw new RuntimeException("verification Code didnt Match");
        }
        return userVerification.getEmail();
    }

    public String generateVerificationCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }

    public void deleteVerificationCode(String email) {
        UserVerification userVerification = verificationRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("This Email is unknown"));
        verificationRepository.delete(userVerification);
    }
}

