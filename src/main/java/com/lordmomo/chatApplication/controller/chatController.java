package com.lordmomo.chatApplication.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class chatController {

    @MessageMapping("/chat.sendMessage")    //tells which url to use to invoke 'sendMessage' method
    @SendTo("/topic/public")    //to which topic or queue we what to send it to
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        //[INFO] So whenever we get a message,which is in @Payload,
        //[INFO] it gets automatically sent to "/topic/public"
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")    //tells which url to use to invoke 'addMessage' method
    @SendTo("/topic/public")
    public ChatMessage addUser (@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        //[INFO] Allows us to establish a connection with the user and the web socket.

        //Add username in web socket session
        headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
        return chatMessage;
    }

}
