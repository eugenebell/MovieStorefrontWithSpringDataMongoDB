package com.cit.eugene.service.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.access.annotation.Secured;

import com.cit.eugene.model.Genre;

/**
 * Used to manage the Genre DAO layer.
 * 
 * @author Eugene Bell
 */
public interface GenreRepository extends MongoRepository<Genre, Long> {

	/**
	 * Get all genres.
	 * 
	 * @return List<Genre>
	 */
	List<Genre> findAll();
	
}
