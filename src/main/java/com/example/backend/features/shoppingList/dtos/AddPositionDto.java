package com.example.backend.features.shoppingList.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPositionDto {
    private String name;
    private int quantity;
}
