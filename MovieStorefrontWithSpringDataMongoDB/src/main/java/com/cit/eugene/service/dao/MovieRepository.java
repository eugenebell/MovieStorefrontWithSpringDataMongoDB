package com.cit.eugene.service.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;

import com.cit.eugene.model.Movie;

/**
 * Used to manage the Movie DAO layer.
 * 
 * @author Eugene Bell
 */
public interface MovieRepository extends MongoRepository<Movie, Long> {

	/**
	 * Get all movies.
	 * 
	 * @return List<Movie>
	 */
	List<Movie> findAll();
	
	/**
	 * Get all movie by genre ID.
	 * 
	 * @param genreID
	 * @return List<Movie>
	 */
	List<Movie> findByGenreID(@Param("genreID") Long genreID);
	
	/**
	 * Get movie by id.
	 * 
	 * @param movieID
	 * @return Movie
	 */
	Movie findByMovieID(Long movieID);
	
}
