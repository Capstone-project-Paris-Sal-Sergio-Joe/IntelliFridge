package com.example.intellifridge.controllers;


import com.example.intellifridge.models.Food;
import com.example.intellifridge.models.Fridge;
import com.example.intellifridge.models.User;
import com.example.intellifridge.repositories.FoodRepository;
import com.example.intellifridge.repositories.FridgeRepository;
import com.example.intellifridge.repositories.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



    @GetMapping("/fridge/add-food")
    public String showAddFood(Model model) {
        model.addAttribute("fridge", new Fridge());
        return "fridge/add-food";
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
//            WHY DOES THIS CHANGE
//        System.out.println(currentUser.getUsername());
//        System.out.println(currentUser.getFridges());
//
//        System.out.println(sameUser.getUsername());
//        System.out.println(sameUser.getFridges());
//        THIS BELOW WORKS BUT IT HARD-CODES THE USER INSTEAD OF GETTING THE CURRENT USER
//        User firstUser = userRepository.getById(1L);
//        firstUser.getFridges().add(fridge);
//        fridgeRepository.save(fridge);

//        MISTAKENLY SHOWS THE CURRENTUSERS FRIDGES AS "NULL"
//        System.out.println(currentUser.getUsername());
//        System.out.println(currentUser.getFridges());
        return "redirect:/profile";
    }


//    public String adFoodToFridge(@ModelAttribute Food food) {
//        Fridge userFridge =  fridgeRepository.getById(1L);
//        food.setName(userFridge.getName());
//        fridgeRepository.save(userFridge);


    @PostMapping("/food/{id}/delete")
    public String deletePost(@PathVariable long id) {
        foodRepository.deleteById(id);

        return "redirect:/fridge";
    }


//sort foods

    //-needs sortFoods functionality

// sort by days tillexpired
// food group,
// alphabetical, etc.

    //-needs sortFoods functionality

    //===============================================================
    @GetMapping("/sortedFoods")
    public ResponseEntity<List<Food>> getAllFoods(@RequestParam(defaultValue = "id,desc") String[] sort) {

        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Food> foods = foodRepository.findAll(Sort.by(orders));

            if (foods.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(foods, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    sort method
    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }


//    @PostMapping("/sort")
//    public String sortFoods(){
//        return "fridge/fridge";
//    }
// sort by days tillexpired
// food group,
// alphabetical, etc.


}
