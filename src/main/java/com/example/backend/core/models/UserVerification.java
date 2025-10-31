package com.example.backend.core.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UserVerification{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String verificationCode;
}
