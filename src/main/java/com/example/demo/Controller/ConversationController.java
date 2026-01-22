package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.AUTH.UserDetailsImpl;
import com.example.demo.Dati.Conversation;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Service.ConversationService;

@RestController
@RequestMapping("/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping
    public ResponseEntity<List<Conversation>> myConversations( @AuthenticationPrincipal UserDetailsImpl userDetails) 
    		throws UserNotFoundException {

        return ResponseEntity.ok(conversationService.getMyConversations(userDetails.getUser().getId()));
    }

}

