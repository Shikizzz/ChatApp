package com.example.chatapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="sender_id")
    private Integer senderId;

    @Column(name="content")
    private String content;

    @Column(name="sessionId")
    private Integer sessionId;

    @Column(name="time")
    private LocalDateTime time;
}
