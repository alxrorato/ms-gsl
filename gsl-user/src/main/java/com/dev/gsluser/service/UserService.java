package com.dev.gsluser.service;

import com.dev.gsluser.entities.User;

public interface UserService {

	User findById(Long id);
	
	User findByEmail(String email);
}
