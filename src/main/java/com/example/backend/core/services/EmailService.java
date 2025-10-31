package com.example.backend.core.services;

import com.example.backend.core.models.UserVerification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    private final JavaMailSender mailSender;

    @Value("${server.app.base-url}")
    private String serverAddress;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationEmail(UserVerification userVerification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userVerification.getEmail());
        message.setSubject("Email Verification Code");
        message.setText(serverAddress + "/emailAuth/" + userVerification.getVerificationCode() + "/" + userVerification.getId());
        mailSender.send(message);
    }
}
