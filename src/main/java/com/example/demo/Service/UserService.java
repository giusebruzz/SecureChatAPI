package com.example.demo.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.Dati.User;
import com.example.demo.Dati.UserStatus;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {
	
	UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public UserStatus calculateStatus(User user) {
	    if (user.getLastSeen() == null) return UserStatus.OFFLINE;

	    LocalDateTime now = LocalDateTime.now();
	    return user.getLastSeen().isAfter(now.minusMinutes(2))
	            ? UserStatus.ONLINE
	            : UserStatus.OFFLINE;
	}
}
