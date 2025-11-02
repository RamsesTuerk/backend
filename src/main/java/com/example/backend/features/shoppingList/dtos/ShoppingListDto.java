package com.example.backend.features.shoppingList.dtos;

import com.example.backend.features.shoppingList.models.ShoppingList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ShoppingListDto {
    private Integer id;
    private String name;
    private boolean active;
    private List<PositionDto> positions;

    public ShoppingListDto(ShoppingList shoppingList) {
        this.id = shoppingList.getId();
        this.name = shoppingList.getName();
        this.active = shoppingList.isActive();
        this.positions = shoppingList.getPositions() != null
                ? shoppingList.getPositions().stream().map(PositionDto::new).toList()
                : List.of();
    }
}
