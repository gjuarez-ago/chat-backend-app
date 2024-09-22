package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

  List<Message> findBySenderAndReceiverOrReceiverAndSender(String sender, String receiver, String reverseSender,
      String reverseReceiver);

  @Query("SELECT m FROM Message m WHERE (m.sender = :user1 AND m.receiver = :user2) OR (m.sender = :user2 AND m.receiver = :user1) ORDER BY m.timestamp ASC")
  List<Message> findMessagesBetweenUsers(@Param("user1") String user1, @Param("user2") String user2);

}
