package com.example.demo.Dati;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Conversation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user1_id")
	private User user1;
	@ManyToOne
	@JoinColumn(name = "user2_id")
	private User user2;
	private LocalDate createdAt;
	
	public Conversation() {
		super();
		this.user1 = null;
		this.user2 = null;
		this.createdAt = null;
	}
	
	@Override
	public String toString() {
		return "id=" + id + ", sender=" + user1 + ", reciever=" + user2 + ", createdAt=" + createdAt;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
}
