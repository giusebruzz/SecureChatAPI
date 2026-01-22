package com.example.demo.Dati;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String userName;
	@NotBlank
	@Email
	@Column(unique = true, nullable = false)
	private String email;
	@NotBlank
	@Size(min = 8, message = "the password has to contain at least 8 cheracters" )
	private String password;
	
	private LocalDate createAt;
	private LocalDateTime lastSeen;
	
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	@OneToMany(mappedBy = "sender")
	@JsonIgnore
	private List<Message>messagesSender;
	@OneToMany(mappedBy = "receiver")
	@JsonIgnore
	private List<Message>messagesReceiver;
	
	public User() {
		super();
		this.userName = "N/A";
		this.email = "N/A";
		this.password = "N/A";
		this.createAt = null;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", createAt=" + createAt + ", messagesSender=" + messagesSender + ", messagesReceiver="
				+ messagesReceiver + "]";
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
	}

	public List<Message> getMessagesSender() {
		return messagesSender;
	}

	public void setMessagesSender(List<Message> messagesSender) {
		this.messagesSender = messagesSender;
	}

	public List<Message> getMessagesReceiver() {
		return messagesReceiver;
	}

	public void setMessagesReceiver(List<Message> messagesReceiver) {
		this.messagesReceiver = messagesReceiver;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public LocalDateTime getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(LocalDateTime lastSeen) {
		this.lastSeen = lastSeen;
	}
	
	
	
	
}
