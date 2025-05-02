package com.example.chatapp.repository;

import com.example.chatapp.model.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession, Integer> {

    void deleteBySessionId(Integer id);
}
