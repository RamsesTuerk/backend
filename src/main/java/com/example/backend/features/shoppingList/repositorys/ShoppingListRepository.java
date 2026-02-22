package com.example.backend.features.shoppingList.repositorys;

import com.example.backend.features.shoppingList.models.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {

}
