package com.example.backend.core.controller;

import com.example.backend.core.Dtos.LoginTokenDto;
import com.example.backend.core.Dtos.LoginUserDto;
import com.example.backend.core.Dtos.RegisterUserDto;
import com.example.backend.core.models.User;
import com.example.backend.core.models.UserVerification;
import com.example.backend.core.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final EmailService emailService;
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final VerificationService verificationService;

    public AuthenticationController(JwtService jwtService, EmailService emailService, UserService userService, AuthenticationService authenticationService, VerificationService verificationService) {
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.verificationService = verificationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUserDto) {
        authenticationService.signup(registerUserDto);
        String verificationCode = verificationService.generateVerificationCode();
        UserVerification userVerification = new UserVerification();
        userVerification.setVerificationCode(verificationCode);
        userVerification.setEmail(registerUserDto.getEmail());
        verificationService.save(userVerification);
        emailService.sendVerificationEmail(userVerification);
        return ResponseEntity.ok("");
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User user = authenticationService.authenticate(loginUserDto);
        return ResponseEntity.ok(new LoginTokenDto(jwtService.generateToken(user), jwtService.getExpirationTime()));
    }

    @GetMapping("/emailVerification/{verificationCode}/{id}")
    public ResponseEntity<?> checkEmail(@PathVariable String verificationCode, @PathVariable Long id) {
        String email = verificationService.verifyCode(id, verificationCode);
        User user = userService.getUserFromEmail(email);
        userService.changeEmailVerificationStatus(user, true);
        verificationService.deleteVerificationCode(email);
        return ResponseEntity.ok("Email verified");
    }
}