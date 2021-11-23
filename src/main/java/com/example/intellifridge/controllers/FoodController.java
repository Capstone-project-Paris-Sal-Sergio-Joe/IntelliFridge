package com.example.intellifridge.controllers;


import com.example.intellifridge.models.Food;
import com.example.intellifridge.models.Fridge;
import com.example.intellifridge.models.ShelfLife;
import com.example.intellifridge.repositories.FoodRepository;
import com.example.intellifridge.repositories.FoodShelfLifeRepository;
import com.example.intellifridge.repositories.FridgeRepository;
import com.example.intellifridge.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;


@Controller
public class FoodController {
private final FoodRepository foodRepository;
private final FridgeRepository fridgeRepository;
private final UserRepository userRepository;
private final FoodShelfLifeRepository foodShelfLifeRepository;

    public FoodController(FoodRepository foodRepository, FridgeRepository fridgeRepository, UserRepository userRepository, FoodShelfLifeRepository foodShelfLifeRepository) {
        this.foodRepository = foodRepository;
        this.fridgeRepository = fridgeRepository;
        this.userRepository = userRepository;
        this.foodShelfLifeRepository = foodShelfLifeRepository;
    }


    @GetMapping("/fridge/{id}/add-food")
    public String showAddFood(@PathVariable long id, Model model) throws Exception {
        Fridge currentFridge = fridgeRepository.getById(id);
        model.addAttribute("fridge", currentFridge);
        model.addAttribute("food", new Food(Timestamp.from(Instant.now())));
        return "fridge/add-food";

    }


    @PostMapping("/fridge/{id}/add-food")
    public String addFood(@PathVariable long id, @ModelAttribute Food food, @RequestParam(name="isInFreezer") String isInFreezer) throws Exception {

        if (isInFreezer.equals("true")) {
            food.setInFreezer(true);
        } else {
            food.setInFreezer(false);
        }

//        EXPIRATION DATE
//        List<ShelfLife> shelfLifeList = foodShelfLifeRepository.findAll();
//        for (int i=0;i<shelfLifeList.size();i++) {
//            ShelfLife shelfLife = shelfLifeList.get(i);
//            if (shelfLife.getName().equalsIgnoreCase(food.getName())) {
//                if (food.isInFreezer() == true) {
//                    food.setExpirationDate(addDays(shelfLife.getShelfLifeFreezerDays(), food.getDateAdded()));
//                } else {
//                    food.setExpirationDate(addDays(shelfLife.getShelfLifeFridgeDays(), food.getDateAdded()));
//                }
//            }
//        }

        List<ShelfLife> shelfLifeList = foodShelfLifeRepository.findAll();
        for (int i=0;i<shelfLifeList.size();i++) {
            ShelfLife shelfLife = shelfLifeList.get(i);
            if (shelfLife.getName().equalsIgnoreCase(food.getName())) {
                Timestamp expirationDate;
                if (food.isInFreezer() == true) {
                    expirationDate = addDays(shelfLife.getShelfLifeFreezerDays(), food.getDateAdded());
                } else {
                    expirationDate = addDays(shelfLife.getShelfLifeFridgeDays(), food.getDateAdded());
                }
                food.setExpirationDate(expirationDate);
            }
        }
        System.out.println(food);

        food.setId(0);
        Fridge currentFridge = fridgeRepository.getById(id);
        food.setFridge(currentFridge);
        foodRepository.save(food);
        return "redirect:/fridge/" + id;
    }

    private Timestamp addDays(int days, Timestamp t1) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(t1);
        cal.add(Calendar.DAY_OF_WEEK, days);
        Timestamp ts = new Timestamp(cal.getTime().getTime());
        return ts;
    }

//    private Long dayToMiliseconds(int days){
//        Long result = Long.valueOf(days * 24 * 60 * 60 * 1000);
//        return result;
//    }
//
//    public Timestamp addDays(int days, Timestamp t1) throws Exception{
//        if(days < 0){
//            throw new Exception("Day in wrong format.");
//        }
//        Long miliseconds = dayToMiliseconds(days);
//        return new Timestamp(t1.getTime() + miliseconds);
//    }
}
