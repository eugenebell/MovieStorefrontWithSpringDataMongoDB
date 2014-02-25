package com.cit.eugene.service.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eugene.model.Movie;
import com.eugene.model.MovieReservation;
import com.eugene.model.VideoStoreMember;
import com.eugene.service.dao.MovieReservationRepository;
import com.eugene.service.dao.VideoStoreMemberRepository;

@ContextConfiguration("file:src/test/resources/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MovieReservationDAOTest {

	@Autowired
	private MovieReservationRepository movieReservationRepository;
	@Autowired
	private VideoStoreMemberRepository videoStoreMemberRepository;

	@Test
	public void testStoreOrUpdateMovieReservation() {
		MovieReservation movieReservation = new MovieReservation();
		movieReservation.setRented(false);
		movieReservation.setReservationDate(new Date());
		Movie movie = new Movie();
		movie.setMovieID("1");
		movieReservation.setMovie(movie);
		VideoStoreMember memberID = new VideoStoreMember();
		memberID.setVideoStoreMemberID("1");
		VideoStoreMember memberID2 =videoStoreMemberRepository.save(memberID);
		movieReservation.setMemberID(memberID2);
		MovieReservation expected = movieReservationRepository.save(movieReservation);
		assertNotNull(expected);
		assertEquals(expected.getMovie().getMovieID(), "1");
		assertEquals(expected.getMemberID().getVideoStoreMemberID(), "1");
	}

	@Test
	public void testDeleteMovieReservation() {
		MovieReservation movieReservation = new MovieReservation();
		movieReservation.setMovieReservationID("33");
		movieReservation.setRented(false);
		Date d = new Date();
		movieReservation.setReservationDate(d);
		Movie movie = new Movie();
		movie.setMovieID("1");
		movieReservation.setMovie(movie);
		VideoStoreMember memberID = new VideoStoreMember();
		memberID.setVideoStoreMemberID("1");
		movieReservation.setMemberID(memberID);
		MovieReservation expected = movieReservationRepository.save(movieReservation);
		assertNotNull(expected);
		assertEquals(d, movieReservation.getReservationDate());
		assertEquals("33", movieReservation.getMovieReservationID());
		try {
			movieReservationRepository.delete(expected);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
