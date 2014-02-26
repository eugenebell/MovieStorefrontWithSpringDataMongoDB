package com.eugene.service.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eugene.model.Movie;
import com.eugene.model.MovieReservation;
import com.eugene.model.User;
import com.eugene.model.VideoStoreMember;
import com.eugene.service.dao.MovieRepository;
import com.eugene.service.dao.UserRepository;
import com.eugene.service.dao.VideoStoreMemberRepository;

@Service
public class MovieServiceImpl implements MovieService {

	private static final Logger LOG = Logger.getLogger(MovieServiceImpl.class);

	private MovieRepository movieRepository;
	private UserRepository userRepository;
	private VideoStoreMemberRepository videoStoreMemberRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	@Autowired
	public void setVideoStoreMemberDAO(VideoStoreMemberRepository videoStoreMemberRepository) {
		this.videoStoreMemberRepository = videoStoreMemberRepository;
	}

	public List<Movie> getMovieListing() {
		return movieRepository.findAll();//getAllMovies();
	}
	
	public List<Movie> getMovieListingByGenreID(String genreID) {
		return movieRepository.findByGenresGenreID(genreID);
	}

	public Movie getMovieByID(String username, String movieID) {
		User u = userRepository.findByUsername(username);
		VideoStoreMember vsm = videoStoreMemberRepository.findByUserUserID(u.getUserID());
		Movie movie = movieRepository.findByMovieID(movieID);
		List<MovieReservation> l = vsm.getMovieReservations();
		if (l != null) {
			for (MovieReservation movieReservation : l) {
					if (movieReservation.getMovie().getMovieID().equals(movie.getMovieID())) {
						movie.setRented(true);
					}
			}
		}
		return movie;
	}
}
