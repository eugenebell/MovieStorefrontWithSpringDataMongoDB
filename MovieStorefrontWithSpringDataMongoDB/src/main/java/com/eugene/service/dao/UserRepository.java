package com.eugene.service.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eugene.model.User;

/**
 * Used to manage the User DAO layer.
 * 
 * @author Eugene Bell
 */
public interface UserRepository extends MongoRepository<User, String> {

	public User findByUsername(String username);
	
}
