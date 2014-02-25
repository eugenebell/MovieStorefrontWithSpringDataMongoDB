package com.eugene.service.business;

import java.util.List;

import com.eugene.model.Movie;

/**
 * Class is for the Management of Movies.
 * 
 * @author Eugene Bell
 */
public interface MovieService {

	/**
	 * Returns the list of Movies.
	 * 
	 * @return List<Movie>
	 */
	public List<Movie> getMovieListing();
	
	/**
	 * Returns the list of Movies that match the selected genre.
	 * 
	 * @param genreID Long 
	 * @return List<Movie>
	 */
	public List<Movie> getMovieListingByGenreID(String genreID);
	
	/**
	 * Returns the movie based on movieID and user name.
	 * 
	 * @param username String
	 * @param movieID Long
	 * @return Movie
	 */
	public Movie getMovieByID(String username, String movieID);
	
}
