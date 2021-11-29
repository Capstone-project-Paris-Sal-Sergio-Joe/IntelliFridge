package com.example.intellifridge.models;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Twilio {

    @Value("${ACCOUNT_SID}")
    public String ACCOUNT_SID;

    @Value("${AUTH_TOKEN}")
    public String AUTH_TOKEN;

    @Value("${PHONE_NUMBER}")
    private String PHONE_NUMBER;

    public void twilioTest(){
        com.twilio.Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("userPhoneNumber"),//send to
                new PhoneNumber(PHONE_NUMBER),//send from
                "hello").create();

        System.out.println(message.getSid());
    }

    public void sendNotification(Food food, User user){
        com.twilio.Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(user.getPhoneNumber()),//send to
                new PhoneNumber(PHONE_NUMBER),//send from
                "your" + food.getName() + "is going to expire soon").create();

        System.out.println(message.getSid());
    }

}
