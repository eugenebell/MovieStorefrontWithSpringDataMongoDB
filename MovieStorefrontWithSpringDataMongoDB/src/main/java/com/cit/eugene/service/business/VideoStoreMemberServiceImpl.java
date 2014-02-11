package com.cit.eugene.service.business;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cit.eugene.model.Account;
import com.cit.eugene.model.Authorities;
import com.cit.eugene.model.MovieReservation;
import com.cit.eugene.model.VideoStoreMember;
import com.cit.eugene.service.dao.MovieRepository;
import com.cit.eugene.service.dao.MovieReservationRepository;
import com.cit.eugene.service.dao.VideoStoreMemberRepository;

@Service
public class VideoStoreMemberServiceImpl implements VideoStoreMemberService {

	private static final Logger LOG = Logger.getLogger(VideoStoreMemberServiceImpl.class);

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieReservationRepository movieReservationRepository;

	@Autowired
	private VideoStoreMemberRepository videoStoreMemberRepository;

	@PostConstruct
	void init() {
		LOG.info("VideoStoreMemberManagerImpl has been Created");
	}

	@PreDestroy
	void destroy() {
		LOG.info("VideoStoreMemberManagerImpl has been Destroyed");
	}

	public void setVideoStoreMemberRepository(VideoStoreMemberRepository videoStoreMemberRepository) {
		this.videoStoreMemberRepository = videoStoreMemberRepository;
	}	

	public void setMovieReservationRepository(MovieReservationRepository movieReservationRepository) {
		this.movieReservationRepository = movieReservationRepository;
	}

	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean reserveMovie(String username, long movieID, boolean rented) {
		VideoStoreMember vsm = getVideoStoreMember(username);
		MovieReservation mr = new MovieReservation();
		mr.setMemberID(vsm);
		mr.setRented(rented);
		mr.setMovie(movieRepository.findByMovieID(movieID));//getMovieByID(movieID));
		mr.setReservationDate(Calendar.getInstance().getTime());
		movieReservationRepository.save(mr);//storeOrUpdateMovieReservation(mr);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean cancelReservedMovie(String username, String reservationID) {
		VideoStoreMember vsm = getVideoStoreMember(username);
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

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public VideoStoreMember getVideoStoreMember(String videoStoreMemberName) {
		return videoStoreMemberRepository.getVideoStoreMemberByName(videoStoreMemberName);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Iterable<VideoStoreMember> getAllVideoStoreMember() {
		return videoStoreMemberRepository.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<MovieReservation> getVideoStoreMembersReservations(Long videoStoreMemberID) {
		return videoStoreMemberRepository.findOne(videoStoreMemberID).getMovieReservations();//getVideoStoreMemberByID(videoStoreMemberID).getMovieReservations();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteVideoStoreMember(Long videoStoreMemberID) {
		videoStoreMemberRepository.delete(videoStoreMemberID);//deleteVideoStoreMember(videoStoreMemberID);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public VideoStoreMember getVideoStoreMemberByID(Long videoStoreMemberID) {
		return videoStoreMemberRepository.findOne(videoStoreMemberID);//getVideoStoreMemberByID(videoStoreMemberID);
	}
}
