package com.example.intellifridge.controllers;

import com.example.intellifridge.models.Food;
import com.example.intellifridge.models.Fridge;
import com.example.intellifridge.models.User;
import com.example.intellifridge.repositories.FoodRepository;
import com.example.intellifridge.repositories.FoodShelfLifeRepository;
import com.example.intellifridge.repositories.FridgeRepository;
import com.example.intellifridge.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DeleteAccountController {

    private FoodRepository foodRepository;
    private FridgeRepository fridgeRepository;
    private UserRepository userRepository;
    private FoodShelfLifeRepository foodShelfLifeRepository;

    public DeleteAccountController(FoodRepository foodRepository, FridgeRepository fridgeRepository, UserRepository userRepository, FoodShelfLifeRepository foodShelfLifeRepository) {
        this.foodRepository = foodRepository;
        this.fridgeRepository = fridgeRepository;
        this.userRepository = userRepository;
        this.foodShelfLifeRepository = foodShelfLifeRepository;
    }

    @GetMapping("/delete-account")
    public String deleteAccountView(){
        return "users/delete-account";
    }

    @PostMapping("/delete-account")
    public String deleteAccount(){

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User sameUser = userRepository.getById(currentUser.getId());

        List<Fridge> usersFridges = sameUser.getFridges();//users fridges

//        for(int i=usersFridges.size() - 1; i>= 0; i--){
        while (usersFridges.size() != 0) {

            for(int i=0; i<usersFridges.size(); i++ ) {


                long currentFridgeId = usersFridges.get(i).getId();

            System.out.println(usersFridges);
            usersFridges.remove(fridgeRepository.getById(currentFridgeId));
            System.out.println(usersFridges);
            List<User> usersInFridge = fridgeRepository.getById(currentFridgeId).getUsers();

            if(usersInFridge.size() == 1){
                System.out.println("Hello");
                List<Food> allFoods = foodRepository.findAllByFridgeId(currentFridgeId);
            for (Food food : allFoods) {
                foodRepository.delete(food);
            }
            fridgeRepository.deleteById(currentFridgeId);
            }
        }
        }
//        }

        fridgeRepository.saveAll(usersFridges);
//        List<User> listOfUsers= userRepository.findAll();
        userRepository.delete(sameUser);
//        userRepository.saveAll(listOfUsers);
        return "redirect: ";
    }

//
//    @PostMapping("/fridge/{id}/delete")
//    public String deleteFridge(@PathVariable long id) {
//
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User sameUser = userRepository.getById(currentUser.getId());
//
//        Fridge fridge = fridgeRepository.getById(id);
//        List<Fridge> fridges = sameUser.getFridges();
//
//        List<User> users = fridgeRepository.getById(id).getUsers();
//        fridges.remove(fridgeRepository.getById(id));
//        if (fridge.getUsers().size() == 1) {
//            List<Food> allFoods = foodRepository.findAllByFridgeId(id);
//            for (Food food : allFoods) {
//                foodRepository.delete(food);
//            }
//            fridgeRepository.deleteById(id);
//        }
//        fridgeRepository.saveAll(fridges);
//        return "redirect:/profile";
//    }
//

}
