package com.example.backend.features.shoppingList.repositorys;

import com.example.backend.features.shoppingList.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {
}
