package com.example.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Message;
import com.example.demo.service.ChatService;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:4200") // Reemplaza con el origen permitido
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestParam String sender, @RequestParam String receiver, @RequestParam String content) {
        Message message = chatService.sendMessage(sender, receiver, content);
        return ResponseEntity.ok(message);
    }


    @GetMapping("/conversation")
    public ResponseEntity<List<Message>> getConversation(@RequestParam String user1, @RequestParam String user2) {
        List<Message> conversation = chatService.getConversation(user1, user2);
        return ResponseEntity.ok(conversation);
    }
}
