package com.example.chatapp.repository;

import com.example.chatapp.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WsChatRepository extends JpaRepository<ChatMessage, Integer> {


}
