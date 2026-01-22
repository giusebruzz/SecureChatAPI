package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Dati.Conversation;
import com.example.demo.Dati.User;

public interface ConversationRepository extends JpaRepository<Conversation,Long>{

	List<Conversation> findByUser1OrUser2(User user1, User user2);

    Optional<Conversation> findByUser1AndUser2OrUser2AndUser1(User user1, User user2,User user1b, User user2b);
}
