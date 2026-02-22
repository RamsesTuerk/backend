package com.example.backend.features.shoppingList.controller;

import com.example.backend.features.shoppingList.dtos.AddPositionDto;
import com.example.backend.features.shoppingList.dtos.CreateShoppingListDto;
import com.example.backend.features.shoppingList.services.ShoppingListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/shoppingList")
@RestController
public class ShoppingListController {

    final private ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getShoppingLists(){
        return ResponseEntity.ok(shoppingListService.findAll());
    }

    @GetMapping(("/{id}"))
    public ResponseEntity<?> getShoppinglist(@PathVariable int id){
        return ResponseEntity.ok(shoppingListService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createShoppingList(@RequestBody CreateShoppingListDto shoppingList){
        shoppingListService.create(shoppingList);
        return ResponseEntity.ok("ShoppingList created");
    }

    @PostMapping("/{id}/addPositions")
    public ResponseEntity<?> addPositions(@RequestBody List<AddPositionDto> positionDtoList, @PathVariable int id){
        shoppingListService.addPositions(positionDtoList, id);
        return ResponseEntity.ok(shoppingListService.findById(id));
    }

    @PatchMapping("/updatePosition/{posId}")
    public ResponseEntity<?> updatePosition(@PathVariable int posId, @RequestBody AddPositionDto positionDto){
        shoppingListService.updatePosition(posId, positionDto.getName());
        return ResponseEntity.ok("Position updated");
    }

    @PatchMapping("/invertPosition/{posId}")
    public ResponseEntity<?> getPositions(@PathVariable int posId){
        shoppingListService.invertPosition(posId);
        return ResponseEntity.ok("Inverted position");
    }

    @PatchMapping("/updateList/{listId}")
    public ResponseEntity<?> updateList(@PathVariable int listId, @RequestBody CreateShoppingListDto createShoppingListDto){
        shoppingListService.updateShoppingList(listId, createShoppingListDto);
        return ResponseEntity.ok("List updated");
    }

    @GetMapping("/deleteList/{listId}")
    public ResponseEntity<?> deleteList(@PathVariable int listId){
        shoppingListService.delete(listId);
        return ResponseEntity.ok("List deleted");
    }

}
