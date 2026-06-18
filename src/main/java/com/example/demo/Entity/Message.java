package com.example.demo.Dati;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String content;
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;
	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private User receiver;
	@ManyToOne
	@JoinColumn(name = "conversation_id")
	private Conversation conversation;
	private LocalDate sentAt;
	
	public Message() {
		super();
		this.content = "N/A";
		this.sender = null;
		this.receiver = null;
		this.conversation = null;
		this.sentAt = null;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", sender=" + sender + ", receiver=" + receiver
				+ ", conversation=" + conversation + ", sentAt=" + sentAt + "]";
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public LocalDate getSentAt() {
		return sentAt;
	}
	public void setSentAt(LocalDate sentAt) {
		this.sentAt = sentAt;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	
	
	

}
