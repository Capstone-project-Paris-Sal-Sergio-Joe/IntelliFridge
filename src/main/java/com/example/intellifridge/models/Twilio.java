package com.example.intellifridge.models;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Twilio {

    @Value("${ACCOUNT_SID}")
    public String ACCOUNT_SID;

    @Value("${AUTH_TOKEN}")
    public String AUTH_TOKEN;

    @Value("${PHONE_NUMBER}")
    private String PHONE_NUMBER;

    public void sendNotification(List<String> foods,User user,int fridgeIndex){//,int fridgeId
        com.twilio.Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

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
                "Hello " + user.getUsername() + " these foods are going to expire soon " + str + " there in your " + user.getFridges().get(fridgeIndex).getName() + " fridge").create();

        System.out.println(message.getSid());
    }

}
