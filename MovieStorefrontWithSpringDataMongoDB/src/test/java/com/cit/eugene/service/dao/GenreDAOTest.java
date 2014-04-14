package com.cit.eugene.service.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eugene.model.Genre;
import com.eugene.service.dao.GenreRepository;

@ContextConfiguration("file:src/test/resources/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class GenreDAOTest {
	
	@Autowired
	private GenreRepository genreRepository;

	@Test
	public void testGetAllGenres() {
		List<Genre> l = genreRepository.findAll();
		assertEquals(11, l.size());
		Genre g = l.get(1);
		assertNotNull(g.getGenreID());
		assertEquals("Adventure", g.getGenreName());
//		assertEquals(11, g.getMovies().size());
//		assertNotNull(g.getMovies());
	}
}
