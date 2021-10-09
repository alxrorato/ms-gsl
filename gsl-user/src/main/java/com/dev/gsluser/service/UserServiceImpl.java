package com.dev.gsluser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.gsluser.entities.User;
import com.dev.gsluser.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public User findById(Long id) {
		User user = repository.findById(id).get();
		return user;
	}

	@Override
	public User findByEmail(String email) {
		User user = repository.findByEmail(email);
		return user;
	}

}
