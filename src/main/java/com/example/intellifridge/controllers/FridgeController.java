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


import java.util.*;


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


    @GetMapping("/fridge/{id}")
    public String showFridge(@PathVariable long id, Model model) {
        Fridge currentFridge = fridgeRepository.getById(id);
        List<Food> foodInFridge = foodRepository.findAllByFridgeId(id);
        model.addAttribute("currentFridge", currentFridge);
        model.addAttribute("foodInFridge", foodInFridge);
        return "fridge/fridge";
    }



    @GetMapping("/fridge/add-fridge")
    public String showAddFridge(Model model) {
        model.addAttribute("fridge", new Fridge());
        return "fridge/add-fridge";
    }


    @PostMapping("/fridge/add-fridge")
    public String createFridge(@ModelAttribute Fridge fridge) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User sameUser = userRepository.getById(currentUser.getId());
        sameUser.getFridges().add(fridge);
        fridgeRepository.save(fridge);
        return "redirect:/profile";
    }




//    @PostMapping("/food/{id}/delete")
//    public String deletePost(@PathVariable long id) {
//        foodRepository.deleteById(id);
//
//        return "redirect:/fridge";
//    }


//sort foods

    //-needs sortFoods functionality

// sort by days tillexpired
// food group,
// alphabetical, etc.


}
