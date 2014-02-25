package com.cit.eugene.service.business;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.eugene.model.Movie;
import com.eugene.model.MovieReservation;
import com.eugene.model.VideoStoreMember;
import com.eugene.service.business.MovieServiceImpl;
import com.eugene.service.dao.MovieRepository;
import com.eugene.service.dao.VideoStoreMemberRepository;

public class MovieServiceImplTest {

	private MovieRepository movieDAORepository = null;
	private MovieServiceImpl movieManagerImpl = null;
	private VideoStoreMemberRepository videoStoreMemberRepository = null;
	private Movie m = new Movie();
	private VideoStoreMember vsm = new VideoStoreMember();
	
	@Before
	public void setUp() throws Exception {
		movieDAORepository = createMock(MovieRepository.class);
		movieManagerImpl = new MovieServiceImpl();
		videoStoreMemberRepository = createMock(VideoStoreMemberRepository.class);
		movieManagerImpl.setMovieRepository(movieDAORepository);	
		movieManagerImpl.setVideoStoreMemberDAO(videoStoreMemberRepository);
		m.setMovieID("1");
		m.setActorsDisplay("actorsDisplay");
		m.setDirectorsDisplay("directorsDisplay");
		m.setProducersDisplay("Bob Nobody");
		m.setDisplayRunTime("160");
		m.setPrice(1.99);
		m.setRating(2);
		m.setTitle("title");
		m.setSummary("summary");
		m.setRented(false);
		m.setStudioDisplay("studioDisplay");
		m.setPosterFileName("posterFileName");
		m.setYear(1999);
		vsm.setMemebershipNumber("sdhu");
		vsm.setName("bob");
		MovieReservation mr = new MovieReservation();
		mr.setMovie(m);
		mr.setMemberID(vsm);
		mr.setReservationDate(new Date());
		List<MovieReservation> l = new ArrayList<MovieReservation>();
		l.add(mr);
		vsm.setMovieReservations(l);
		vsm.setVideoStoreMemberID("2");
	}
	
	@After
	public void tearDown() {

	}

	@Test
	public void testSetMovieRepository() {
		assertNotNull(movieDAORepository);
		assertNotNull(movieManagerImpl);
	}

	@Test
	public void testGetMovieListing() {
		expect(movieDAORepository.findAll()).andReturn(new ArrayList<Movie>());
		replay(movieDAORepository);
		List<Movie> l2 = movieManagerImpl.getMovieListing();
		assertNotNull(l2);
		verify(movieDAORepository);
	}

	@Test
	public void testGetMovieListingByGenreID() {
		List<Movie> l = new ArrayList<Movie>();
		l.add(m);
		expect(movieDAORepository.findByGenresGenreID("1")).andReturn(l);
		replay(movieDAORepository);
		List<Movie> l2 = movieManagerImpl.getMovieListingByGenreID("1");
		assertNotNull(l2);
		verify(movieDAORepository);
	}

	@Test
	public void testGetMovieByID() {
		expect(videoStoreMemberRepository.getVideoStoreMemberByName("bob")).andReturn(vsm);
		expect(movieDAORepository.findByMovieID("1")).andReturn(m);
		replay(videoStoreMemberRepository);
		replay(movieDAORepository);
		assertEquals(m, movieManagerImpl.getMovieByID("bob", "1"));
		verify(movieDAORepository);
	}

}
