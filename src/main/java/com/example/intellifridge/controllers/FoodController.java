package com.example.intellifridge.controllers;


import com.example.intellifridge.models.Food;
import com.example.intellifridge.models.Fridge;
import com.example.intellifridge.repositories.FoodRepository;
import com.example.intellifridge.repositories.FridgeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FoodController {
private final FoodRepository foodRepository;
private final FridgeRepository fridgeRepository;

    public FoodController(FoodRepository foodRepository, FridgeRepository fridgeRepository) {
        this.foodRepository = foodRepository;
        this.fridgeRepository = fridgeRepository;
    }

    ///View the add food menu


    @GetMapping("/fridge/add-food")
    public String showAddFood(Model model) {
        model.addAttribute("food", new Food());
        return "fridge/add-food";
    }


    ///Add food to fridge based on ID


    @PostMapping("/fridge/{id}/add-food")
    public String addFood(@ModelAttribute Food food, @PathVariable long id){

       foodRepository.save(food);
        return "redirect:/fridge";
    }


}
