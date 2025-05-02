package com.example.chatapp.service;

import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.model.ChatMessageDTO;
import com.example.chatapp.repository.WsChatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatService {

    private WsChatRepository wsChatRepository;

    public ChatService(WsChatRepository wsChatRepository) {
        this.wsChatRepository = wsChatRepository;
    }

    public void persistMessage(ChatMessageDTO msg, Integer id) {
        ChatMessage message = new ChatMessage();
        message.setSenderId(id);
        message.setContent(msg.getContent());
        message.setTime(LocalDateTime.now());
        message.setSessionId(id);
        wsChatRepository.save(message);
    }
}
