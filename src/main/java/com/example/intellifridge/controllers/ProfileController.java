package com.example.intellifridge.controllers;

import com.example.intellifridge.models.Fridge;
import com.example.intellifridge.models.User;
import com.example.intellifridge.repositories.FridgeRepository;
import com.example.intellifridge.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProfileController {

    private FridgeRepository fridgeDao;
    private UserRepository userDao;

    public ProfileController(FridgeRepository fridgeDao, UserRepository userDao) {
        this.fridgeDao = fridgeDao;
        this.userDao = userDao;
    }

    @GetMapping("/profile")
    public String show(Model model){
        User currentUser = userDao.getById(2L);
        List<Fridge> userFridges = userDao.getById(2L).getFridges();
        model.addAttribute("user",currentUser);
        model.addAttribute("fridge", userFridges);
        return "profile/profile";
    }

}