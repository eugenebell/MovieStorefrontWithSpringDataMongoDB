package com.eugene.service.business;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eugene.model.Account;
import com.eugene.model.Authorities;
import com.eugene.model.MovieReservation;
import com.eugene.model.User;
import com.eugene.model.VideoStoreMember;
import com.eugene.service.dao.MovieRepository;
import com.eugene.service.dao.MovieReservationRepository;
import com.eugene.service.dao.UserRepository;
import com.eugene.service.dao.VideoStoreMemberRepository;

@Service(value="videoStoreMemberService")
public class VideoStoreMemberServiceImpl implements VideoStoreMemberService {

	private static final Logger LOG = Logger.getLogger(VideoStoreMemberServiceImpl.class);

	private MovieRepository movieRepository;
	private UserRepository userRepository;
	private MovieReservationRepository movieReservationRepository;
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
	public void setMovieReservationRepository(MovieReservationRepository movieReservationRepository) {
		this.movieReservationRepository = movieReservationRepository;
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
		Authorities a = new Authorities();
		a.setAuthority("ROLE_USER");
		a.setUsers(videoStoreMember.getUser());
		Set<Authorities> s = new HashSet<Authorities>();
		s.add(a);
		Account account = new Account(0.0);
		videoStoreMember.setAccount(account);
		videoStoreMember.getUser().setAuthoritieses(s);
		videoStoreMember.getUser().setEnabled(true);
		return videoStoreMemberRepository.save(videoStoreMember);
	}

	public boolean reserveMovie(String username, String movieID, boolean rented) {
		VideoStoreMember vsm = getVideoStoreMember(userRepository.findByUsername(username).getUserID());
		MovieReservation mr = new MovieReservation();
		mr.setMemberID(vsm);
		mr.setRented(rented);
		mr.setMovie(movieRepository.findByMovieID(movieID));//getMovieByID(movieID));
		mr.setReservationDate(Calendar.getInstance().getTime());
		movieReservationRepository.save(mr);//storeOrUpdateMovieReservation(mr);
		return true;
	}

	public boolean cancelReservedMovie(String username, String reservationID) {
		VideoStoreMember vsm = getVideoStoreMember(userRepository.findByUsername(username).getUserID());
		List<MovieReservation> l = vsm.getMovieReservations();
		MovieReservation toBeRemoved = null;
		for (MovieReservation mr : l) {
			if (!mr.getRented()) {
				if (mr.getMovie().getMovieID().equals(reservationID)) {
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
		return videoStoreMemberRepository.findByUserUserID(userID);
	}

	public boolean rentedMovie(VideoStoreMember vsm, String reservationID) {
		List<MovieReservation> l = vsm.getMovieReservations();
		for (MovieReservation mr : l) {
			if (mr.getMovieReservationID().equals(reservationID)) {
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
