package com.example.backend.core.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {
    private String email;
    private String password;
    private String fcm;

    public  LoginUserDto(String email, String password, String fcm) {
        this.email = email;
        this.password = password;
        this.fcm = fcm;
    }
}
