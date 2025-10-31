package com.example.backend.core.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginTokenDto {
    private String token;
    private long expiresIn;

}
