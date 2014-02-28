package com.eugene.service.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eugene.model.Account;
import com.eugene.model.Authorities;
import com.eugene.model.Movie;
import com.eugene.model.MovieReservation;
import com.eugene.model.User;
import com.eugene.model.VideoStoreMember;
import com.eugene.service.dao.MovieRepository;
import com.eugene.service.dao.UserRepository;
import com.eugene.service.dao.VideoStoreMemberRepository;

@Service(value="videoStoreMemberService")
public class VideoStoreMemberServiceImpl implements VideoStoreMemberService {

	private static final Logger LOG = Logger.getLogger(VideoStoreMemberServiceImpl.class);

	private MovieRepository movieRepository;
	private UserRepository userRepository;
	private VideoStoreMemberRepository videoStoreMemberRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setVideoStoreMemberRepository(VideoStoreMemberRepository videoStoreMemberRepository) {
		this.videoStoreMemberRepository = videoStoreMemberRepository;
	}	

	@Autowired
	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public VideoStoreMember storeVideoStoreMember(VideoStoreMember videoStoreMember) {
		// PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		// String pwd =
		// passwordEncoder.encodePassword(videoStoreMember.getUser().getPassword(),
		// "MySalt");
		Account account = new Account(0.0);
		videoStoreMember.setAccount(account);

		User uUser = new User();
		Authorities a = new Authorities();
		a.setAuthority("ROLE_USER");
		Set<Authorities> aAuthoritieses = new HashSet<Authorities>();
		aAuthoritieses.add(a);
		uUser.setAuthoritieses(aAuthoritieses);
		uUser.setEnabled(true);
		uUser.setPassword(videoStoreMember.getUser().getPassword());
		uUser.setUsername(videoStoreMember.getUser().getUsername());
		uUser = userRepository.save(uUser);
		
		videoStoreMember.setUser(uUser);
		return videoStoreMemberRepository.save(videoStoreMember);
	}

	public boolean reserveMovie(String username, String movieID, boolean rented) {
		LOG.debug("Reserve movie [" + movieID + "] for user [" + username + "]");
		VideoStoreMember vsm = getVideoStoreMember(userRepository.findByUsername(username).getUserID());
		MovieReservation mr = new MovieReservation();
		mr.setRented(rented);
		Movie m = movieRepository.findByMovieID(movieID);
		mr.setMovie(m);
		mr.setReservationDate(Calendar.getInstance().getTime());

		if (vsm.getMovieReservations() != null) {
			vsm.getMovieReservations().add(mr);
		} else {
			List<MovieReservation> l = new ArrayList<MovieReservation>();
			l.add(mr);
			vsm.setMovieReservations(l);
		}
		videoStoreMemberRepository.save(vsm);
		return true;
	}

	public boolean cancelReservedMovie(String username, String movieID) {
		VideoStoreMember vsm = getVideoStoreMember(userRepository.findByUsername(username).getUserID());
		List<MovieReservation> l = vsm.getMovieReservations();
		MovieReservation toBeRemoved = null;
		for (MovieReservation mr : l) {
			if (!mr.getRented()) {
				if (mr.getMovie().getMovieID().equals(movieID)) {
					toBeRemoved = mr;
					break;
				}
			}
		}
		if (toBeRemoved != null) {
			vsm.getMovieReservations().remove(toBeRemoved);
		}
		videoStoreMemberRepository.save(vsm);
		return true;
	}

	public VideoStoreMember getVideoStoreMember(String userID) {
		VideoStoreMember vsm = videoStoreMemberRepository.findByUserUserID(userID);
		List<MovieReservation> l = vsm.getMovieReservations();
		if (l != null) {
			List<MovieReservation> nonRented = new ArrayList<MovieReservation>();
			for (MovieReservation mr : l) {
				if (!mr.getRented()) {
					nonRented.add(mr);
				}
			}
			vsm.setMovieReservations(nonRented);
		}
		return vsm;
	}

	public boolean rentedMovie(VideoStoreMember vsm, String movieID) {
		List<MovieReservation> l = vsm.getMovieReservations();
		for (MovieReservation mr : l) {
			if (mr.getMovie().getMovieID().equals(movieID)) {
				//Add the cost of the movie to the users account total
				Account acc = vsm.getAccount();
				double newTotal = acc.getTotal() + mr.getMovie().getPrice();
				acc.setTotal(newTotal);
				vsm.setAccount(acc);
				mr.setRented(true);
			}
		}
		videoStoreMemberRepository.save(vsm);
		return true;
	}

	public Iterable<VideoStoreMember> getAllVideoStoreMember() {
		return videoStoreMemberRepository.findAll();
	}

	public List<MovieReservation> getVideoStoreMembersReservations(String videoStoreMemberID) {
		return videoStoreMemberRepository.findOne(videoStoreMemberID).getMovieReservations();
	}

	public void deleteVideoStoreMember(String videoStoreMemberID) {
		videoStoreMemberRepository.delete(videoStoreMemberID);
	}

	public VideoStoreMember getVideoStoreMemberByID(String videoStoreMemberID) {
		return videoStoreMemberRepository.findOne(videoStoreMemberID);
	}
}
