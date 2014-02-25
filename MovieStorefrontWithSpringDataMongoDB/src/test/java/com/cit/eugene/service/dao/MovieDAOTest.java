package com.cit.eugene.service.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eugene.model.Movie;
import com.eugene.service.dao.GenreRepository;
import com.eugene.service.dao.MovieRepository;

@ContextConfiguration("file:src/test/resources/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MovieDAOTest {

	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private MovieRepository movieRepository;

	@Test
	public void testGetAllMovies() {
		List<Movie> l = movieRepository.findAll();
		assertEquals(25, l.size());
	}

	@Test
	public void testGetMovieListingByGenreID() {
		
		List<Movie> l1  = movieRepository.findByGenresGenreID("2");
		assertEquals(11, l1.size());
		List<Movie> l2  = movieRepository.findByGenresGenreID("5");
		assertEquals(5, l2.size());
		List<Movie> l3  = movieRepository.findByGenresGenreID("6");
		assertEquals(15, l3.size());
		List<Movie> l4  = movieRepository.findByGenresGenreID("9");
		assertEquals(3, l4.size());
	}

	@Test
	public void testGetMovieByID() {
		Movie m = movieRepository.findByMovieID("1");
		assertNotNull(m);
		assertEquals("13 Assassins", m.getTitle());
		assertEquals("13_Assassins.jpg", m.getPosterFileName());
		assertEquals("A group of assassins come together for a suicide mission to kill an evil lord.", m.getSummary());
		assertEquals(2010, m.getYear().intValue());
		assertEquals("Takashi Miike", m.getDirectorsDisplay());
		assertEquals("", m.getProducersDisplay());
		assertEquals("141 min", m.getDisplayRunTime());
		assertEquals("", m.getStudioDisplay());
		assertEquals(false, m.isRented());
		assertEquals("3.99", m.getPrice().toString());
		assertEquals(2, m.getRating().intValue());
		assertEquals(3, m.getGenres().size());
	}
	
//	@Test
//	public void testGetMovieByIDReturningNull() {
//		Movie m = jpaMovie.findByMovieID(10000l);
//		assertNull(m);
//	}

}
