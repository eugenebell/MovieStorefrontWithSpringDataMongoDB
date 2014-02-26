package com.eugene.service.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eugene.model.Genre;

/**
 * Used to manage the Genre DAO layer.
 * 
 * @author Eugene Bell
 */
public interface GenreRepository extends MongoRepository<Genre, String> {

	/**
	 * Get all genres.
	 * 
	 * @return List<Genre>
	 */
	List<Genre> findAll();
	
}
