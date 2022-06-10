package com.secure.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.jwt.entity.User;

public interface Userrepository extends JpaRepository<User, Integer> {

	User findByUserName(String username);
	
	

}
