package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public ChatService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Message sendMessage(String senderUsername, String receiverUsername, String content) {
        User sender = userRepository.findByUsername(senderUsername);
        User receiver = userRepository.findByUsername(receiverUsername);
        
        if (sender == null || receiver == null) {
            throw new RuntimeException("Sender or receiver not found");
        }
        
        Message message = new Message();
        message.setSender(sender.getUsername());
        message.setReceiver(receiver.getUsername());
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public List<Message> getConversation(String user1Username, String user2Username) {
        User user1 = userRepository.findByUsername(user1Username);
        User user2 = userRepository.findByUsername(user2Username);
        
        if (user1 == null || user2 == null) {
            throw new RuntimeException("One of the users not found");
        }
        
        return messageRepository.findMessagesBetweenUsers(user1.getUsername(), user2.getUsername());
    }
    
}
