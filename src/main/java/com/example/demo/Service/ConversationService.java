package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Dati.Conversation;
import com.example.demo.Dati.User;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Repository.ConversationRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class ConversationService {

    ConversationRepository conversationRepository;
    UserRepository userRep;
   
    public ConversationService(ConversationRepository conversationRepository, UserRepository userRep) {
        this.conversationRepository = conversationRepository;
        this.userRep = userRep;
    }

    public List<Conversation> getMyConversations(Long userId) throws UserNotFoundException {
        User me = userRep.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return conversationRepository.findByUser1OrUser2(me, me);
    }
    
    public Conversation getOrCreateConversation(Long myId, Long otherId) throws UserNotFoundException {
        User me = userRep.findById(myId).orElseThrow(() -> new UserNotFoundException("User not found"));

        User other = userRep.findById(otherId).orElseThrow(() -> new UserNotFoundException("Other user not found"));

        return conversationRepository.findByUser1AndUser2OrUser2AndUser1(me, other, other, me) .orElseGet(() -> {
                    Conversation c = new Conversation();
                    c.setUser1(me);
                    c.setUser2(other);
                    return conversationRepository.save(c);
                    });
   }
}


