package com.example.intellifridge.controllers;


import com.example.intellifridge.models.Food;
import com.example.intellifridge.models.Fridge;
import com.example.intellifridge.models.User;
import com.example.intellifridge.repositories.FoodRepository;
import com.example.intellifridge.repositories.FridgeRepository;
import com.example.intellifridge.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;


@Controller
public class FoodController {
private final FoodRepository foodRepository;
private final FridgeRepository fridgeRepository;
private final UserRepository userRepository;

    public FoodController(FoodRepository foodRepository, FridgeRepository fridgeRepository, UserRepository userRepository) {
        this.foodRepository = foodRepository;
        this.fridgeRepository = fridgeRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/fridge/{id}/add-food")
    public String showAddFood(@PathVariable long id, Model model) {
        Fridge currentFridge = fridgeRepository.getById(id);
        model.addAttribute("fridge", currentFridge);
        model.addAttribute("food", new Food(Timestamp.from(Instant.now())));
        return "fridge/add-food";
    }


    @PostMapping("/fridge/{id}/add-food")
    public String addFood(@PathVariable long id, @ModelAttribute Food food, @RequestParam(name="isInFreezer") String isInFreezer){
        if (isInFreezer.equals("true")) {
            food.setInFreezer(true);
        } else {
            food.setInFreezer(false);
        }
        food.setId(0);
        Fridge currentFridge = fridgeRepository.getById(id);
        food.setFridge(currentFridge);
        foodRepository.save(food);
        return "redirect:/fridge/" + id;
    }
}
