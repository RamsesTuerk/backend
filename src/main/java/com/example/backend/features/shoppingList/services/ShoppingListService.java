package com.example.backend.features.shoppingList.services;

import com.example.backend.features.shoppingList.dtos.AddPositionDto;
import com.example.backend.features.shoppingList.dtos.CreateShoppingListDto;
import com.example.backend.features.shoppingList.dtos.ShoppingListDto;
import com.example.backend.features.shoppingList.models.Position;
import com.example.backend.features.shoppingList.models.ShoppingList;
import com.example.backend.features.shoppingList.repositorys.PositionRepository;
import com.example.backend.features.shoppingList.repositorys.ShoppingListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShoppingListService {
    final private ShoppingListRepository shoppingListRepository;
    final private PositionRepository positionRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, PositionRepository positionRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.positionRepository = positionRepository;
    }

    public List<ShoppingListDto> findAll() {
        List<ShoppingList> shoppingLists = shoppingListRepository.findAll();

        List<ShoppingListDto> shoppingListDtoList = new ArrayList<>();
        for (ShoppingList shoppingList : shoppingLists) {
            shoppingListDtoList.add(new ShoppingListDto(shoppingList));
        }
        return shoppingListDtoList;
    }

    public ShoppingListDto findById(int id) {
        ShoppingList shoppingList = shoppingListRepository.findById(id).orElseThrow(() -> new RuntimeException("ShoppingList not found"));

        return new ShoppingListDto(shoppingList);
    }


    public void create(CreateShoppingListDto shoppingListDto) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName(shoppingListDto.getName());
        shoppingList.setColor(shoppingListDto.getColor());
        shoppingList.setActive(true);
        shoppingListRepository.save(shoppingList);
    }

    public void addPositions(List<AddPositionDto> positionsDto, int shoppingListId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId).orElseThrow(() -> new RuntimeException("ShoppingList not found"));

        for (AddPositionDto positionDto : positionsDto) {
            Position position = new Position(positionDto, shoppingList);
            shoppingList.getPositions().add(position);
            positionRepository.save(position);
        }
        shoppingListRepository.save(shoppingList);
    }

    public void invertPosition(int positionId) {
        Position position = positionRepository.findById(positionId).orElseThrow(() -> new RuntimeException("Position not found"));
        position.setSold(!position.isSold());
        positionRepository.save(position);
    }
}
