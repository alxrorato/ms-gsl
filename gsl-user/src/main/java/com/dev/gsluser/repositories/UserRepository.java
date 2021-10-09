package com.dev.gsluser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.gsluser.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
}
