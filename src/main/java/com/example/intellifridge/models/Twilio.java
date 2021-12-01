package com.example.intellifridge.models;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class Twilio {

    @Value("${ACCOUNT_SID}")
    public String ACCOUNT_SID;

    @Value("${AUTH_TOKEN}")
    public String AUTH_TOKEN;

    @Value("${PHONE_NUMBER}")
    private String PHONE_NUMBER;

//    public void twilioTest(){
//        com.twilio.Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message.creator(new PhoneNumber("userPhoneNumber"),//send to
//                new PhoneNumber(PHONE_NUMBER),//send from
//                "hello").create();
//
//        System.out.println(message.getSid());
//    }

//    public void sendNotification(List<Food> foods, User user){
//        com.twilio.Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        for(int i=0; i<foods.size(); i++){
//            foods.get(i).getName();
//        }
//
//        SimpleDateFormat foodDate = new SimpleDateFormat("MM/dd/yyyy");
//        String formattedDate = foodDate.format(food.getExpirationDate());
//
//        Message message = Message.creator(new PhoneNumber(user.getPhoneNumber()),//send to
//                new PhoneNumber(PHONE_NUMBER),//send from
//                "Your " + food.getName() + " is going to expire on " + formattedDate + " its from your " + food.getFridge().getName() + " fridge.").create();
//
//        System.out.println(message.getSid());
//    }

    public void sendNotification(List<String> foods,User user){//,int fridgeId
        com.twilio.Twilio.init(ACCOUNT_SID, AUTH_TOKEN);


//        for(int i=0; i<foods.size(); i++){
////            System.out.print(foods.get(i).getName() + " " + foods.get(i).getExpirationDate() + ", ");
//            output = " " + foods.get(i).getName() + " " + foods.get(i).getExpirationDate() + ", ";
//
//        }

        StringBuilder strbul=new StringBuilder();
        for(String str : foods)
        {
            strbul.append(str);
            //for adding comma between elements
            strbul.append(",");
        }
        String str=strbul.toString();

        System.out.println(str);

        Message message = Message.creator(new PhoneNumber(user.getPhoneNumber()),//send to
                new PhoneNumber(PHONE_NUMBER),//send from
                "Hello " + user.getUsername() + " these foods are going to expire soon " + str + " there in your ").create();//+ user.getFridges().get(fridgeId).getName()

        System.out.println(message.getSid());
    }

}
