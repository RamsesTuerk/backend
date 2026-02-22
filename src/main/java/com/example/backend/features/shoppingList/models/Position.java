package com.example.backend.features.shoppingList.models;

import com.example.backend.features.shoppingList.dtos.AddPositionDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    private String name;
    private int quantity;
    private boolean sold;

    @ManyToOne
    @JoinColumn(name = "shopping_list_id")
    private ShoppingList shoppingList;

    public Position(AddPositionDto addPositionDto, ShoppingList shoppingList) {
        this.name = addPositionDto.getName();
        this.quantity = addPositionDto.getQuantity();
        this.sold = false;
        this.shoppingList = shoppingList;
    }
}
