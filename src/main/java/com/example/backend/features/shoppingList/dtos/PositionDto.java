package com.example.backend.features.shoppingList.dtos;

import com.example.backend.features.shoppingList.models.Position;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionDto {
    private Integer id;
    private String name;
    private int quantity;
    private boolean sold;

    public PositionDto(Position position) {
        this.id = position.getId();
        this.name = position.getName();
        this.quantity = position.getQuantity();
        this.sold = position.isSold();
    }
}
