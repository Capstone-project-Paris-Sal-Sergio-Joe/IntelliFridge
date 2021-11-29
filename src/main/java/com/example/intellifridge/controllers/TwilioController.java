package com.example.intellifridge.controllers;

import com.example.intellifridge.models.Food;
import com.example.intellifridge.models.Twilio;
import com.example.intellifridge.models.User;
import com.example.intellifridge.repositories.FoodRepository;
import com.example.intellifridge.repositories.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;

@Controller
public class TwilioController {

    private Twilio twilio;
    private UserRepository userRepository;
    private FoodRepository foodRepository;

    public TwilioController(Twilio twilio, UserRepository userRepository, FoodRepository foodRepository) {
        this.twilio = twilio;
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
    }
//86400000
    @Scheduled(fixedRate = 15000)
    public void Notification(){
        //add a for loop to go through ever user and there food and check if they have any food that is about expire
//        Timestamp foodNotification;
//        Food food = foodRepository.getById(1L);
//        foodNotification = SubtractDays(-2,foodRepository.getById(1L).getExpirationDate());
//        User user = userRepository.getById(1L);
//        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
//        if(foodNotification == currentDate){
//            twilio.sendNotification(food,user);
//        }
        List<User> users = userRepository.findAll();

        for(int i=0; i<users.size(); i++){
            System.out.println(i);
        }

    }

    private Timestamp SubtractDays(int days, Timestamp t1) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(t1);
        cal.add(Calendar.DAY_OF_WEEK, days);
        Timestamp ts = new Timestamp(cal.getTime().getTime());
        return ts;
    }

//    public static void main(String[] args) {
//        Notification();
//    }
}
