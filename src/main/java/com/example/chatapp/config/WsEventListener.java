package com.example.chatapp.config;

import com.example.chatapp.service.ChatSessionService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.example.chatapp.model.ChatMessageDTO;
import com.example.chatapp.model.ChatMessageType;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WsEventListener {

        private final SimpMessageSendingOperations messageSendingOperations;
        private ChatSessionService chatSessionService;

    public WsEventListener(SimpMessageSendingOperations messageSendingOperations, ChatSessionService chatSessionService) {
        this.messageSendingOperations = messageSendingOperations;
        this.chatSessionService = chatSessionService;
    }

    @EventListener
    public void handleWsDisconnectListener( SessionDisconnectEvent event){
        //To listen to another even, create the another method with NewEvent as argument.
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username !=null){
            log.info("User disconnected: {} ", username);
            var message = ChatMessageDTO.builder()
                    .type(ChatMessageType.LEAVE)
                    .sender(username)
                    .build();
            //pass the message to the broker specific topic : public
            messageSendingOperations.convertAndSend("/topic/public",message);
        }

        String id = (String) headerAccessor.getSessionAttributes().get("id");
        if(id !=null){
            chatSessionService.deleteBySessionId(Integer.valueOf(id));
        }
    }
    


}
