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
        String hash = passwordEncoder.encode(user.getPassword());
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User sameUser = userDao.getById(currentUser.getId());
//        String startingPhoneNumber = sameUser.getPhoneNumber();
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
