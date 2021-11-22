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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String addFood(@PathVariable long id, @ModelAttribute Food food){
        System.out.println(food.getName());
        System.out.println(food.getDateAdded());
        System.out.println(food.getIsInFreezer());
        Fridge currentFridge = fridgeRepository.getById(id);
        food.setFridge(currentFridge);
        foodRepository.save(food);
        return "redirect:/fridge/" + id;
    }
}
