package com.example.intellifridge.controllers;


import com.example.intellifridge.models.Food;
import com.example.intellifridge.models.Fridge;
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
    public String addFood(@PathVariable long id, @ModelAttribute Food food) {
//
//        if (isInFreezer.equals("true")) {
//            food.setInFreezer(true);
//        } else {
//            food.setInFreezer(false);
//        }
//
//
//        List<ShelfLife> shelfLifeList = foodShelfLifeRepository.findAll();
//        for (int i=0;i<shelfLifeList.size();i++) {
//            ShelfLife shelfLife = shelfLifeList.get(i);
//            if (shelfLife.getName().equalsIgnoreCase(food.getName())) {
//                Timestamp expirationDate;
//                food.setShelfLife(shelfLife);
//                if (food.isInFreezer() == true) {
//                    expirationDate = addDays(shelfLife.getShelfLifeFreezerDays(), food.getDateAdded());
//                } else {
//                    expirationDate = addDays(shelfLife.getShelfLifeFridgeDays(), food.getDateAdded());
//                }
//                food.setExpirationDate(expirationDate);
//            }
//        }
//        System.out.println(food);

        food.setId(0);
        Fridge currentFridge = fridgeRepository.getById(id);
        food.setFridge(currentFridge);
        foodRepository.save(food);
        return "redirect:/fridge/" + id;
    }




  //  /===============================================================
    @PostMapping("/fridge/{id}/sort")
    public  String  getAllFoods(@PathVariable String id, @ModelAttribute Food food) {
        String currentFridge = fridgeRepository.getById(id);
//        food.sortAsc(currentFridge);
fridgeRepository.findAllByNameAsc(currentFridge);

//      foodRepository.save(food);

//fridgeRepository.findAllByNameAsc(id);
//        model.addAttribute("id",fridgeFoodAsc);

//        return "/fridge/fridge";

        return "redirect:/fridge/" + id;
//            List<Sort.Order> orders = new ArrayList<Sort.Order>();
//           if (sort[0].contains(",")) {
//                // will sort more than 2 fields
//                // sortOrder="field, direction"
//                for (String sortOrder : sort) {
//                    String[] _sort = sortOrder.split(",");
//                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
//                }
//            } else {
//                // sort=[field, direction]
//                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
//            }
//
//            List<Food> foods = foodRepository.findAll(Sort.by(orders));
//
//            if (foods.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(foods, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    return "redirect:/fridge/{id}";
    }    private Timestamp addDays(int days, Timestamp t1) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(t1);
        cal.add(Calendar.DAY_OF_WEEK, days);
        Timestamp ts = new Timestamp(cal.getTime().getTime());
        return ts;
    }

}
