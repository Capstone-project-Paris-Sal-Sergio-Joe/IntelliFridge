package com.example.intellifridge.controllers;

import com.example.intellifridge.models.User;
import com.example.intellifridge.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){

        List<User> users = userDao.findAll();
        for (User otherUser: users) {
            if (otherUser.getUsername().equalsIgnoreCase(user.getUsername())) {
                return "redirect:/sign-up?error=usernameAlreadyExists";
            } else if (otherUser.getEmail() == null) {
                continue;
            } else if (otherUser.getEmail().equalsIgnoreCase("")) {
                continue;
            } else if (otherUser.getEmail().equalsIgnoreCase(user.getEmail())) {
                return "redirect:/sign-up?error=emailAlreadyExists";
            } else if (otherUser.getPhoneNumber() == null) {
                continue;
            } else if (otherUser.getPhoneNumber().equalsIgnoreCase("")) {
                continue;
            } else if (otherUser.getPhoneNumber().equalsIgnoreCase(user.getPhoneNumber())) {
                return "redirect:/sign-up?error=phoneNumberAlreadyExists";
            }
        }

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
//        if(startingPhoneNumber.equals(null))
        userDao.save(user);
        return "redirect:/login";
    }


//profile image upload
    @PostMapping("/profile/{id}/editImage")
    public String imageUpload(@PathVariable long id, @RequestParam(name = "image") String profilePicture) {
        User user = userDao.getById(id);

        user.setProfilePicture(profilePicture);
        userDao.save(user);

        return "redirect:/profile/";
    }

}
