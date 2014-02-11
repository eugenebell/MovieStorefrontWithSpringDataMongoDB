package com.cit.eugene.service.business;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cit.eugene.model.Movie;
import com.cit.eugene.model.MovieReservation;
import com.cit.eugene.model.VideoStoreMember;
import com.cit.eugene.service.dao.MovieRepository;
import com.cit.eugene.service.dao.VideoStoreMemberRepository;

@Service
public class MovieServiceImpl implements MovieService {

	private static final Logger LOG = Logger.getLogger(MovieServiceImpl.class);
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private VideoStoreMemberRepository videoStoreMemberRepository;
	
	@PostConstruct
	void init() {
		LOG.info("MovieManagerImpl has been Created");
	} 
	
	@PreDestroy
	void destroy() {
		LOG.info("MovieManagerImpl has been Destroyed");
	}
	
	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	public void setVideoStoreMemberDAO(VideoStoreMemberRepository videoStoreMemberRepository) {
		this.videoStoreMemberRepository = videoStoreMemberRepository;
	}

	@Transactional(readOnly=true)
	public List<Movie> getMovieListing() {
		return movieRepository.findAll();//getAllMovies();
	}
	
	@Transactional(readOnly=true)
	public List<Movie> getMovieListingByGenreID(Long genreID) {
		return movieRepository.findByGenreID(genreID);
	}

	@Transactional(readOnly=true)
	public Movie getMovieByID(String username, Long movieID) {
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
