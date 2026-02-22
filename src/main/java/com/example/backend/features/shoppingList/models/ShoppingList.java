package com.example.backend.features.shoppingList.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    private String name;
    private String color;
    private boolean active;

    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL)
    @OrderBy("sold ASC, name ASC")
    private Set<Position> positions = new HashSet<>();
    ;
}
