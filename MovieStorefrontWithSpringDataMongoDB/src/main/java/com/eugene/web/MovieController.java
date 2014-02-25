package com.eugene.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eugene.model.Genre;
import com.eugene.model.Movie;
import com.eugene.service.business.GenreService;
import com.eugene.service.business.MovieService;

@Controller
public class MovieController {

	private MovieService movieManager;
	private GenreService genreManager;

	// constructor injection
	@Autowired
	public MovieController(MovieService movieManager, GenreService genreManager) {
		this.movieManager = movieManager;
		this.genreManager = genreManager;
	}

	@RequestMapping("/movieListing")
	public void getMoviesSummary(Model model) {
		model.addAttribute("genres", genreManager.getGenreListing());
		model.addAttribute("movies", movieManager.getMovieListing());
	}
	
	@RequestMapping(value = "/genres", method = RequestMethod.GET)
	public @ResponseBody List<Genre> getGenres() {
		return genreManager.getGenreListing();
	}

	@RequestMapping(value = "/movieListing/genre/{genreid}", method = RequestMethod.GET)
	public @ResponseBody List<Movie> getMovieListByGenre(@PathVariable("genreid") String id) {
		return movieManager.getMovieListingByGenreID(id);
	}

	@RequestMapping(value = "/movieListing/movie/{movieid}", method = RequestMethod.GET)
	public @ResponseBody Movie getMovieInfoByID(@PathVariable("movieid") String id) {
		Authentication aut = SecurityContextHolder.getContext().getAuthentication();
		return movieManager.getMovieByID(aut.getName(), id);
	}
}
