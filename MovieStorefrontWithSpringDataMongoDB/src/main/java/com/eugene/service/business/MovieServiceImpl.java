package com.eugene.service.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eugene.model.Movie;
import com.eugene.model.MovieReservation;
import com.eugene.model.VideoStoreMember;
import com.eugene.service.dao.MovieRepository;
import com.eugene.service.dao.VideoStoreMemberRepository;

@Service
public class MovieServiceImpl implements MovieService {

	private static final Logger LOG = Logger.getLogger(MovieServiceImpl.class);
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private VideoStoreMemberRepository videoStoreMemberRepository;

	
	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	public void setVideoStoreMemberDAO(VideoStoreMemberRepository videoStoreMemberRepository) {
		this.videoStoreMemberRepository = videoStoreMemberRepository;
	}

	public List<Movie> getMovieListing() {
		return movieRepository.findAll();//getAllMovies();
	}
	
	public List<Movie> getMovieListingByGenreID(String genreID) {
		List<Movie> m = movieRepository.findByGenresGenreID(genreID);
		return m;
	}

	public Movie getMovieByID(String username, String movieID) {
		VideoStoreMember vsm = videoStoreMemberRepository.getVideoStoreMemberByName(username);
		Movie movie = movieRepository.findByMovieID(movieID);
		List<MovieReservation> l = vsm.getMovieReservations();
		for (MovieReservation movieReservation : l) {
				if (movieReservation.getMovie().getMovieID().equals(movie.getMovieID())) {
					movie.setRented(true);
				}
		}
		return movie;
	}
}
