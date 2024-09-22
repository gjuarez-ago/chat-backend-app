package com.example.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.example.demo.entity.Message;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    public void sendMessage(Message message) {
        // Asegúrate de que el receptor sea correcto
        String destination = "/topic/chat/" + message.getReceiver();
        messagingTemplate.convertAndSend(destination, message);
    }
}
