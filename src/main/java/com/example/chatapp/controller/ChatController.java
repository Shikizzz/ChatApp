package com.example.chatapp.controller;

import com.example.chatapp.model.ChatMessageDTO;
import com.example.chatapp.service.ChatSessionService;
import com.example.chatapp.service.ChatService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private ChatService wsChatService;
    private ChatSessionService chatSessionService;


    public ChatController(ChatService wsChatService, ChatSessionService chatSessionService) {
        this.wsChatService = wsChatService;
        this.chatSessionService = chatSessionService;
    }

    @MessageMapping("chat.sendMessage/{id}")  // Maps messages sent to "chat.sendMessage/{id}" WebSocket destination
    @SendTo("/topic/{id}")  // Specifies that the return message will be sent to "/topic/{id}"
    public ChatMessageDTO sendMessage(@Payload ChatMessageDTO msg, @DestinationVariable String id) {
        // Log the sender and content of the message for debugging
        System.out.println("Message received from " + msg.getSender() + ": " + msg.getContent());
        this.wsChatService.persistMessage(msg, Integer.valueOf(id));
        
        // Broadcast the message to all subscribers on the "/topic/{id}" topic
        return msg;
    }

    @MessageMapping("chat.addUser/{id}")  // Maps messages sent to "chat.addUser/{id}" WebSocket destination
    @SendTo("/topic/{id}")  // Specifies that the return message will be sent to "/topic/{id}"
    public ChatMessageDTO addUser(@Payload ChatMessageDTO msg, SimpMessageHeaderAccessor headerAccessor, @DestinationVariable String id) {
        // Store the username in the WebSocket session attributes
        headerAccessor.getSessionAttributes().put("username", msg.getSender());

        if (!headerAccessor.getSessionAttributes().containsKey("id")) {
            headerAccessor.getSessionAttributes().put("id", id);
            this.chatSessionService.persistSession(Integer.valueOf(id));
        }
        
        // Log when a user joins the chat
        System.out.println("User joined: " + msg.getSender());
        
        // Broadcast the user join event to all subscribers on the "/topic/id" topic
        return msg;
    }

}
