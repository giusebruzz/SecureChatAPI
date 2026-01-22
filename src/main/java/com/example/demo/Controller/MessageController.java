package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dati.Conversation;
import com.example.demo.Dati.Message;
import com.example.demo.Dati.User;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Repository.ConversationRepository;
import com.example.demo.Repository.MessageRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.MessageService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/messages")
@SecurityRequirement(name = "bearerAuth")
public class MessageController {

    MessageService messageService;
    UserRepository userRep;
    MessageRepository messageRep;
    ConversationRepository conversetionRep;

    public MessageController(MessageService messageService, UserRepository userRep, MessageRepository messageRep, ConversationRepository conversetionRep) {
        this.messageService = messageService;
        this.userRep = userRep;
        this.messageRep = messageRep;
        this.conversetionRep = conversetionRep;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@AuthenticationPrincipal UserDetails userDetails,@RequestParam String receiverEmail,
    		@RequestParam String content) throws UserNotFoundException {

        Message msg = messageService.sendMessage(
                userDetails.getUsername(),
                receiverEmail,
                content
        );

        return ResponseEntity.ok(msg);
    }
    
    @GetMapping("/conversations/{conversationId}")
    public ResponseEntity<List<Message>> getMessagesByConversation(@PathVariable Long conversationId,
    		@AuthenticationPrincipal UserDetails userDetails) {

        Conversation conversation = conversetionRep.findById(conversationId).orElseThrow();

        return ResponseEntity.ok(messageRep.findByConversationOrderBySentAt(conversation));
    }
    
} 


