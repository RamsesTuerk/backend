package com.example.backend.features.shoppingList.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateShoppingListDto {
    private String name;
    private int color;
    private List<AddPositionDto> positions;
}
