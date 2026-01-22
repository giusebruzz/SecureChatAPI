package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Dati.Conversation;
import com.example.demo.Dati.Message;
import com.example.demo.Dati.User;

public interface MessageRepository extends JpaRepository<Message,Long>{

	List<Message> findBySenderAndReceiverOrderBySentAt(User sender, User receiver,User sender2,User receiver2);
	
	List<Message> findByConversationId(Long conversationId);
	
	List<Message> findByConversationOrderBySentAt(Conversation conversation);


}
