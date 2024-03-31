package com.chat.ChatApplication.controllers;

import com.chat.ChatApplication.models.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @MessageMapping("/message")  //ye annotation is used jab bhi koi message bhejega wo iss url to use karega
    @SendTo("/topic/return-to") //Or jis jis client ne iss url ko subscribe kiya hoga uss uss ko ye message chala jaayegaa
    private Message getContent(@RequestBody Message message){
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return message;
    }
}
