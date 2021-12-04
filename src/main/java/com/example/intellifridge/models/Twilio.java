package com.example.intellifridge.models;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class Twilio {

   @Value("${ACCOUNT_SID}")
   public String ACCOUNT_SID;

   @Value("${AUTH_TOKEN}")
   public String AUTH_TOKEN;

   @Value("${PHONE_NUMBER}")
   private String PHONE_NUMBER;

   public Timestamp SubtractDays(int days, Timestamp t1) {
       Calendar cal = Calendar.getInstance();
       cal.setTime(t1);
       cal.add(Calendar.DAY_OF_WEEK, days);
       Timestamp ts = new Timestamp(cal.getTime().getTime());
       return ts;
   }
   public void sendNotification(List<Food> foods,User user,int fridgeIndex){
       com.twilio.Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

//        StringBuilder strbul=new StringBuilder();
//        for(String str : foods)
//        {
//            strbul.append(str);
//            //for adding comma between elements
//            strbul.append(",");
//        }
//        String str=strbul.toString();
//
//        System.out.println(str);
       SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");

       Timestamp currentDate = new Timestamp(System.currentTimeMillis());

       StringBuilder strbul=new StringBuilder();
       strbul.append("\n------------------\n");
       strbul.append("Food: Expires\n");
       for(Food food : foods)
       {
           Timestamp foodNotificationOneDay = SubtractDays(-1,food.getExpirationDate());
           Timestamp foodNotificationTwoDay = SubtractDays(-2,food.getExpirationDate());
//            Timestamp foodDate = food.getExpirationDate();
           if(date.format(food.getExpirationDate()).equals(date.format(currentDate))){
               strbul.append(food.getName() + ": today\n");

           }

           else if(date.format(currentDate).equals(date.format(foodNotificationOneDay))){
               strbul.append(food.getName() +  ": tomorrow\n");

           }

           else if(date.format(currentDate).equals(date.format(foodNotificationTwoDay))){
               strbul.append(food.getName() + ": 2 days\n");

           }


//            strbul.append(food.getName() + " "+ date.format(foodDate));
           //for adding comma between elements
//            strbul.append(",");
       }
       String str=strbul.toString();

       System.out.println(str);

//        for(int i=0; i<foods.size(); i++){
//            foods.get(i).getName()
//        }


       Message message = Message.creator(new PhoneNumber(user.getPhoneNumber()),//send to
               new PhoneNumber(PHONE_NUMBER),//send from
               "Hello " + user.getUsername() + " these foods are going to expire soon\nFridge: " + user.getFridges().get(fridgeIndex).getName() + str + "------------------\n").create();

       System.out.println(message.getSid());
   }

}
