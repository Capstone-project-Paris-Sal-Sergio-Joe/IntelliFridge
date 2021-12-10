package com.example.intellifridge.controllers;

import com.example.intellifridge.models.Fridge;
import com.example.intellifridge.models.User;
import com.example.intellifridge.repositories.FridgeRepository;
import com.example.intellifridge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User sameUser = userDao.getById(currentUser.getId());
        List<Fridge> userFridges = sameUser.getFridges();
        model.addAttribute("user",sameUser);
        model.addAttribute("fridge", userFridges);
        return "profile/profile";
    }


    @PostMapping("/profile/add-user-to-fridge/{id}")
    public String addUserToFridge(@PathVariable long id, @RequestParam(name = "addByUserName") String name, Model model){

        List<User> users = userDao.findAll();
        List<String> usernamesAndEmails = new ArrayList<>();
        for (User user: users) {
            usernamesAndEmails.add(user.getUsername());
            usernamesAndEmails.add(user.getEmail());
        }
        User findUser = null;
        for (int i=0;i<usernamesAndEmails.size();i++) {
            if (usernamesAndEmails.get(i).equalsIgnoreCase(name)) {
                findUser = userDao.findByUsername(name);
                if (findUser == null) {
                    findUser = userDao.findByEmail(name);
                }
            }
        }
        if (findUser == null) {

            return "redirect:/profile?error=null";
        }

        if (findUser.getIsPrivate() == true) {
            return "redirect:/profile?error=privateUser";
        }


        Fridge findFridge = fridgeDao.getById(id);
        List<Fridge> userFridges = userDao.getById(findUser.getId()).getFridges();
        for(int i=0; i<userFridges.size(); i++){
            if(findFridge.getId() == userFridges.get(i).getId()){
                return "redirect:/profile?error=current";
            }
        }

        findUser.getFridges().add(findFridge);
        userDao.save(findUser);
        return "redirect:/profile?userAdded=true";
}

    @PostMapping("/profile/{id}/edit")
    public String updateProfile(@PathVariable long id, @RequestParam String username, @RequestParam String email, @RequestParam String phoneNumber, @RequestParam Boolean notifications, @RequestParam Boolean privacy){

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User sameUser = userDao.getById(currentUser.getId());

        List<User> otherUsers = userDao.findUsersByIdIsNot(sameUser.getId());

        for (User otherUser : otherUsers) {
            if (otherUser.getUsername().equalsIgnoreCase(username)) {
                return "redirect:/profile?error=usernameAlreadyExists";
            } else if (otherUser.getEmail().equalsIgnoreCase(email)) {
                return "redirect:/profile?error=emailAlreadyExists";
            } else if (otherUser.getPhoneNumber() == null) {
                continue;
            } else if (otherUser.getPhoneNumber().equalsIgnoreCase("")) {
                continue;
            } else if (otherUser.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return "redirect:/profile?error=phoneNumberAlreadyExists";
            }
        }

        return "redirect:/profile";
    }

}
