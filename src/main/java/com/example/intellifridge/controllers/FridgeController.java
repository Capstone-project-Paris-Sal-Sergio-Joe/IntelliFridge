package com.example.intellifridge.controllers;


import com.example.intellifridge.models.Food;
import com.example.intellifridge.models.Fridge;
import com.example.intellifridge.models.User;
import com.example.intellifridge.repositories.FoodRepository;
import com.example.intellifridge.repositories.FridgeRepository;
import com.example.intellifridge.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class FridgeController {
private FoodRepository foodRepository;
private FridgeRepository fridgeRepository;
private UserRepository userRepository;

    public FridgeController(FoodRepository foodRepository, FridgeRepository fridgeRepository, UserRepository userRepository) {
        this.foodRepository = foodRepository;
        this.fridgeRepository = fridgeRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/fridge")
    public String showFridge() {
        return "/fridge";
    }


    @GetMapping("/fridge/create")
    public String create(Model model) {
        model.addAttribute("fridge", new Fridge());
        return "fridge/create";
    }


    @PostMapping("/fridge/create")
    public String createFridge(@ModelAttribute Fridge fridge) {
        User userFridge = (User) userRepository.getById(1L);
        fridge.setUsers((List<User>) userFridge);
        fridgeRepository.save(fridge);
        return "redirect:/fridge";
    }


    @GetMapping("/fridge/adFood")
    public String adFood(Model model) {
        model.addAttribute("food", new Food());
        return "fridge/adFood";
    }


    @PostMapping("/fridge/adFood")
    public String adFoodTOFridge(@ModelAttribute Food food) {
        User userFridge = (User) userRepository.getById(1L);
        food.setName((List<User>) userFridge);
        fridgeRepository.save(fridge);
        return "redirect:/fridge";
    }



}