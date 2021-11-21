package com.dev.gsluser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.gsluser.entities.User;
import com.dev.gsluser.service.UserService;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User user = userService.findById(id);
		return ResponseEntity.ok(user);
	}	

	@GetMapping(value = "/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email) {
		User user = userService.findByEmail(email);
		return ResponseEntity.ok(user);
	}

	@GetMapping("listar")
	public ResponseEntity<List<User>> listarUsuarios() {
		List<User> list = userService.buscarUsuarios();
		return ResponseEntity.ok(list);
	}	

}
