package com.example.chatapp.service;

import com.example.chatapp.model.ChatSession;
import com.example.chatapp.repository.ChatSessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class ChatSessionService {
    private ChatSessionRepository chatSessionRepository;

    public ChatSessionService(ChatSessionRepository chatSessionRepository) {
        this.chatSessionRepository = chatSessionRepository;
    }

    public void persistSession(Integer id) {
        ChatSession chatSession = new ChatSession();
        chatSession.setSessionId(id);
        chatSessionRepository.save(chatSession);
    }
    @Transactional
    public void deleteBySessionId(Integer id){
        this.chatSessionRepository.deleteBySessionId(id);
    }
}
