package com.example.intellifridge.controllers;

import com.example.intellifridge.models.Food;
import com.example.intellifridge.models.Twilio;
import com.example.intellifridge.models.User;
import com.example.intellifridge.repositories.FoodRepository;
import com.example.intellifridge.repositories.FridgeRepository;
import com.example.intellifridge.repositories.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Controller
@Configuration
@EnableScheduling
public class TwilioController {

    private Twilio twilio;
    private UserRepository userRepository;
    private FoodRepository foodRepository;
    private FridgeRepository fridgeRepository;

    public TwilioController(Twilio twilio, UserRepository userRepository, FoodRepository foodRepository, FridgeRepository fridgeRepository) {
        this.twilio = twilio;
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
        this.fridgeRepository = fridgeRepository;
    }



    private Timestamp SubtractDays(int days, Timestamp t1) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(t1);
        cal.add(Calendar.DAY_OF_WEEK, days);
        Timestamp ts = new Timestamp(cal.getTime().getTime());
        return ts;
    }

//    @Scheduled(fixedRate = 86400000) // one day use this one for deployment

    @Scheduled(fixedRate = 30000)// for testing
    @Transactional// keep both line off if you dont want get a lot of messages during testing

    public void notification() {

        List<User> users = userRepository.findAll();

        SimpleDateFormat foodDate = new SimpleDateFormat("MM/dd/yyyy");

        Timestamp currentDate = new Timestamp(System.currentTimeMillis());

        for (int i = 0; i < users.size(); i++) {//loops through all users
            for (int ii = 0; ii < users.get(i).getFridges().size(); ii++) {//gets all users fridges
                List<Food> notifyForFood = new ArrayList<Food>();//this holds all the foods that are going to expire soon. it starts off empty
                List<Food> foodInFridge = foodRepository.findAllByFridgeIdOrderByExpirationDate(users.get(i).getFridges().get(ii).getId());//gets fridge id so if can find all the food in that fridge
                for (int iii = 0; iii < foodInFridge.size(); iii++) {
                    if(users.get(i).isNotifications() == true){
                    Timestamp foodNotificationTwoDays = SubtractDays(-2, foodRepository.getById(foodInFridge.get(iii).getId()).getExpirationDate());//makes a timestamp by subtracting 2 days before the food will expire
                   Timestamp foodNotificationOneDay = SubtractDays(-1, foodRepository.getById(foodInFridge.get(iii).getId()).getExpirationDate());
                    if (foodDate.format(currentDate).equals(foodDate.format(foodInFridge.get(iii).getExpirationDate()))) { //this will check if the current food is going to expire today
//                        notifyForFood.add(foodInFridge.get(iii).getName());// if food is about to expire in the next 2 days it will be added to a notifyForFood
                        notifyForFood.add(foodInFridge.get(iii));

                    } else if( foodDate.format(currentDate).equals(foodDate.format(foodNotificationOneDay))){//this will check if the current food is going to expire in the 1 next days
                        notifyForFood.add(foodInFridge.get(iii));

                    }else if(foodDate.format(currentDate).equals(foodDate.format(foodNotificationTwoDays))){//this will check if the current food is going to expire in the 2 next days
                        notifyForFood.add(foodInFridge.get(iii));
                    }
                    }
                }
                if(notifyForFood.size() >= 1){//check to see if there any food in the list that needs to be sent through a message if there is no food then it not send a message
                    twilio.sendNotification(notifyForFood, users.get(i),users.get(i).getFridges().indexOf(users.get(i).getFridges().get(ii)));
                    notifyForFood.clear();//this clears the notifyForFood list after it is done  sending the notification
                }
            }
        }
    }
}
