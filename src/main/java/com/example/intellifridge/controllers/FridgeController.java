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

    @GetMapping("/yo/{name}")
    @ResponseBody
    public String showYo(@PathVariable String name) {
        return "hello " + name;
    }


    @GetMapping("/fridge/{id}")
    public String showFridge(@PathVariable long id, Model model) {
        Fridge currentFridge = fridgeRepository.getById(id);
        List<ShelfLife> shelfLifeList = foodShelfLifeRepository.findAll();
        List<Food> foodInFridge = foodRepository.findAllByFridgeId(id);
        Collections.sort(foodInFridge, new SortByExpirationDate());
        model.addAttribute("currentFridge", currentFridge);
        model.addAttribute("foodInFridge", foodInFridge);
        model.addAttribute("food", new Food(Timestamp.from(Instant.now())));
        model.addAttribute("shelfLives", shelfLifeList);
        return "fridge/fridge";
    }

    @GetMapping("/fridge/{id}/{sortByMethod}")
    public String showSortedFridge(@PathVariable long id, @PathVariable String sortByMethod, Model model) {
        Fridge currentFridge = fridgeRepository.getById(id);
        List<ShelfLife> shelfLifeList = foodShelfLifeRepository.findAll();
        List<Food> foodInFridge = null;
        if (sortByMethod.equalsIgnoreCase("name")) {
            foodInFridge = foodRepository.findAllByFridgeIdOrderByName(id);
        } else if (sortByMethod.equalsIgnoreCase("dateAdded")) {
            foodInFridge = foodRepository.findAllByFridgeIdOrderByDateAddedDesc(id);
        } else if (sortByMethod.equalsIgnoreCase("expirationDate")) {
            foodInFridge = foodRepository.findAllByFridgeIdOrderByExpirationDate(id);
        }
        model.addAttribute("currentFridge", currentFridge);
        model.addAttribute("foodInFridge", foodInFridge);
        model.addAttribute("food", new Food(Timestamp.from(Instant.now())));
        model.addAttribute("shelfLives", shelfLifeList);
        return "fridge/fridge";
    }

    class SortByExpirationDate implements Comparator<Food> {
        // Used for sorting in ascending order of ID
        public int compare(Food a, Food b) {
            return a.getExpirationDate().compareTo(b.getExpirationDate());
        }
    }

    @PostMapping("/profile/add-fridge")
    public String createFridge(@ModelAttribute Fridge newFridge) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User sameUser = userRepository.getById(currentUser.getId());
        sameUser.getFridges().add(newFridge);
        fridgeRepository.save(newFridge);
        return "redirect:/profile";
    }

    @PostMapping("/fridge/{id}/delete")
    public String deleteFridge(@PathVariable long id) {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User sameUser = userRepository.getById(currentUser.getId());
        Fridge fridge = fridgeRepository.getById(id);
        List<Fridge> fridges = sameUser.getFridges();
        List<User> users = fridgeRepository.getById(id).getUsers();
        fridges.remove(fridgeRepository.getById(id));
        System.out.println(fridge.getUsers().size());
        if (fridge.getUsers().size() == 1) {
            List<Food> allFoods = foodRepository.findAllByFridgeId(id);
            for (Food food : allFoods) {
                foodRepository.delete(food);
            }
            fridgeRepository.deleteById(id);
        }
        fridgeRepository.saveAll(fridges);
        return "redirect:/profile";
    }


}
