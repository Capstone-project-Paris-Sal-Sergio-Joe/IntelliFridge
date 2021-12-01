package com.example.intellifridge.controllers;


import com.example.intellifridge.models.Food;
import com.example.intellifridge.models.Fridge;
import com.example.intellifridge.models.ShelfLife;
import com.example.intellifridge.models.User;
import com.example.intellifridge.repositories.FoodRepository;
import com.example.intellifridge.repositories.FoodShelfLifeRepository;
import com.example.intellifridge.repositories.FridgeRepository;
import com.example.intellifridge.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;


@Controller
public class FridgeController {
    private FoodRepository foodRepository;
    private FridgeRepository fridgeRepository;
    private UserRepository userRepository;
    private FoodShelfLifeRepository foodShelfLifeRepository;

    public FridgeController(FoodRepository foodRepository, FridgeRepository fridgeRepository, UserRepository userRepository, FoodShelfLifeRepository foodShelfLifeRepository) {
        this.foodRepository = foodRepository;
        this.fridgeRepository = fridgeRepository;
        this.userRepository = userRepository;
        this.foodShelfLifeRepository = foodShelfLifeRepository;
    }


    @GetMapping("/fridge/{id}")
    public String showFridge(@PathVariable long id, Model model) {
        Fridge currentFridge = fridgeRepository.getById(id);
        List<ShelfLife> shelfLifeList = foodShelfLifeRepository.findAll();
        List<Food> foodInFridge = foodRepository.findAllByFridgeId(id);
        model.addAttribute("currentFridge", currentFridge);
        model.addAttribute("foodInFridge", foodInFridge);
        model.addAttribute("food", new Food(Timestamp.from(Instant.now())));
        model.addAttribute("shelfLives", shelfLifeList);
        return "fridge/fridge";
    }

//    @GetMapping("/fridge/{id}/add-food")
//    public String showAddFood(@PathVariable long id, Model model) throws Exception {
//        Fridge currentFridge = fridgeRepository.getById(id);
//        List<ShelfLife> shelfLifeList = foodShelfLifeRepository.findAll();
//        model.addAttribute("fridge", currentFridge);
//        model.addAttribute("food", new Food(Timestamp.from(Instant.now())));
//        model.addAttribute("shelfLives", shelfLifeList);
//        return "fridge/add-food";
//
//    }



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






    @PostMapping("/fridge/{fridgeId}/food/{foodId}/delete")
    public String deletePost(@PathVariable long fridgeId,@PathVariable long foodId ) {
        foodRepository.deleteById(foodId);
        return "redirect:/fridge/" + fridgeId;
    }




//sort foods
    //    pagination and sorting methods complete
    //    placed in different branch to not cause merge conflicts
//


}
