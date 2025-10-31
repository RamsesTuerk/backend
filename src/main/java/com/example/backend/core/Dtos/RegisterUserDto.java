package com.example.backend.core.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto extends LoginUserDto {

    private String name;

    public RegisterUserDto(String email, String password, String name) {
        super(email, password);
        this.name = name;
    }
}