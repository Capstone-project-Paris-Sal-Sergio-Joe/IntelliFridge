package com.example.intellifridge.controllers;

import com.example.intellifridge.models.Food;
import com.example.intellifridge.models.Fridge;
import com.example.intellifridge.models.Twilio;
import com.example.intellifridge.models.User;
import com.example.intellifridge.repositories.FoodRepository;
import com.example.intellifridge.repositories.FridgeRepository;
import com.example.intellifridge.repositories.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.Notification;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
//@Configuration
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

//    @Scheduled(fixedRate = 86400000)
//    @Scheduled(fixedRate = 60000)
    public void notification(){
//        add a for loop to go through ever user and there food and check if they have any food that is about expire
//        Food food = foodRepository.getById(1L);
//        Timestamp foodNotification = SubtractDays(-2,foodRepository.getById(1L).getExpirationDate());
//        User user = userRepository.getById(1L);
//        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
//        if(foodNotification == currentDate){
//            twilio.sendNotification(food,user);
//        }
        List<User> users = userRepository.findAll();

        for(int i=0; i<users.size(); i++){
            System.out.println(users.get(i));
        }

    }

    private Timestamp SubtractDays(int days, Timestamp t1) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(t1);
        cal.add(Calendar.DAY_OF_WEEK, days);
        Timestamp ts = new Timestamp(cal.getTime().getTime());
        return ts;
    }

//    @GetMapping("twilio")
//    @ResponseBody
@Scheduled(fixedRate = 30000)
    public void hello(){
    System.out.println("hello");
    }

//@Scheduled(fixedRate = 30000)
    @GetMapping("twilio")
    @ResponseBody
    public void test() {

        List<User> users = userRepository.findAll();

//        Timestamp foodNotification = SubtractDays(0, foodRepository.getById(5L).getExpirationDate());//add this to food for loop after testing

        SimpleDateFormat foodDate = new SimpleDateFormat("MM/dd/yyyy");

        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
//        List<String> notifyForFood = new ArrayList<String>();

//        for (int i=0;i<fridges.size(); i++){
//            fridges.get(i)
//        }

        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getFridges());//gets all users fridges
            for (int ii = 0; ii < users.get(i).getFridges().size(); ii++) {
//                System.out.println(users.indexOf(users.get(i).getFridges().get(ii)));
                System.out.println(users.get(i).getFridges().get(ii).getName());//gets fridges name
                System.out.println(users.get(i).getFridges().get(ii).getId());//gets fridges id
                List<String> notifyForFood = new ArrayList<String>();
                List<Food> foodInFridge = foodRepository.findAllByFridgeId(users.get(i).getFridges().get(ii).getId());
                System.out.println(foodInFridge);
                for (int iii = 0; iii < foodInFridge.size(); iii++) {
                    Timestamp foodNotification = SubtractDays(-2, foodRepository.getById(foodInFridge.get(iii).getId()).getExpirationDate());//add this to food for loop after testing
                    if (foodDate.format(currentDate).equals(foodDate.format(foodNotification))) { //this will check if the current food is going to to expire
                        notifyForFood.add(foodInFridge.get(iii).getName());
                    }
//
                }
                if(notifyForFood.size() >= 1){
                    twilio.sendNotification(notifyForFood, users.get(i));//users.get(i).getFridges().get(ii).getId()
                    notifyForFood.clear();
                }
//                twilio.sendNotification(notifyForFood, users.get(i));
//                notifyForFood.clear();

//        List<Food> foodInFridge = foodRepository.findAllByFridgeId(id);
//        for(int i=0; i<foodInFridge.size(); i++){
//            if(foodDate.format(foodInFridge.get(i).getExpirationDate()).equals(foodDate.format(foodNotification))){ //this will check if the current food is going to to expire
//                notifyForFood.add(foodInFridge.get(i).getName());
////                twilio.sendNotification(notifyForFood,user);
//                notifyForFood.clear();
//            }
            }


//            User user = userRepository.getById(1L);
//            twilio.sendNotification(notifyForFood, user);

        }


//    public static void main(String[] args) {
//        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
//        System.out.println(currentDate);
//        SimpleDateFormat foodDate = new SimpleDateFormat("MM/dd/yyyy");
//        String formattedDate = foodDate.format(currentDate);
//        System.out.println(formattedDate);
//    }
    }
}
