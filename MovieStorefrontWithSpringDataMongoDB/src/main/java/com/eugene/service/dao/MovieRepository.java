package com.eugene.service.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eugene.model.Movie;

/**
 * Used to manage the Movie DAO layer.
 * 
 * @author Eugene Bell
 */
public interface MovieRepository extends MongoRepository<Movie, String> {

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
	List<Movie> findByGenresGenreID(String genreID);
	
	/**
	 * Get movie by id.
	 * 
	 * @param movieID
	 * @return Movie
	 */
	Movie findByMovieID(String movieID);
	
}
