package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Dati.Conversation;
import com.example.demo.Dati.Message;
import com.example.demo.Dati.User;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Repository.ConversationRepository;
import com.example.demo.Repository.MessageRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class MessageService {
	
	
    MessageRepository messageRepository;
    UserRepository userRepository;
    ConversationRepository conversationRepository;
    ConversationService conversationService;

    public MessageService(MessageRepository messageRepository,ConversationRepository conversationRepository
    		,UserRepository userRepository, ConversationService conversationService) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
        this.conversationService = conversationService;
    }

    public List<Message> getMessages(Long conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }

    public Message sendMessage(String senderEmail,String receiverEmail,String content) throws UserNotFoundException {

        User sender = userRepository.findByEmail(senderEmail).orElseThrow(() -> new UserNotFoundException("sender not found for "+senderEmail));

        User receiver = userRepository.findByEmail(receiverEmail) .orElseThrow(() -> new UserNotFoundException("receiver not found for "+senderEmail));

        Conversation conversation = conversationService.getOrCreateConversation(sender.getId(), receiver.getId());

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setConversation(conversation);
        message.setContent(content);
        message.setSentAt(LocalDate.now());

        return messageRepository.save(message);
    }

}
