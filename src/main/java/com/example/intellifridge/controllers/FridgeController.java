package com.example.intellifridge.controllers;


import com.example.intellifridge.models.Fridge;
import com.example.intellifridge.repositories.FoodRepository;
import com.example.intellifridge.repositories.FridgeRepository;
import com.example.intellifridge.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


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
        Fridge userFridge = fridgeRepository.getById(1L);
        fridge.setName(userFridge);
        fridgeRepository.save(fridge);
        return "redirect:/fridge";
    }



}
