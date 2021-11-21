package com.dev.gsluser.service;

import java.util.List;

import com.dev.gsluser.entities.User;

public interface UserService {

	User findById(Long id);
	
	User findByEmail(String email);

	List<User> buscarUsuarios();
}
