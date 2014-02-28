package com.eugene.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.eugene.model.MovieReservation;
import com.eugene.model.VideoStoreMember;
import com.eugene.service.business.VideoStoreMemberService;

@Controller
public class VideoStoreMemberController {

	private VideoStoreMemberService videoStoreMemberManager;

	// constructor injection
	@Autowired
	public VideoStoreMemberController(VideoStoreMemberService videoStoreMemberManager) {
		this.videoStoreMemberManager = videoStoreMemberManager;
	}

	@RequestMapping(value = "/createVideoStoreMember", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public VideoStoreMember getVideoStoreMember() {
		return new VideoStoreMember();
	}

	@RequestMapping(value = "/movieListing/listAllVideoStoreMember", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Iterable<VideoStoreMember> getAllVideoStoreMember() {
		return videoStoreMemberManager.getAllVideoStoreMember();
	}

	@RequestMapping(value = "/movieListing/createVideoStoreMember", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void createVideoStoreMember(@RequestBody VideoStoreMember videoStoreMember) {
		videoStoreMemberManager.storeVideoStoreMember(videoStoreMember);
	}

	@RequestMapping(value = "/movieListing/deleteVideoStoreMember/{videoStoreMemberID}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteVideoStoreMembe(@PathVariable("videoStoreMemberID") String videoStoreMemberID) {
		videoStoreMemberManager.deleteVideoStoreMember(videoStoreMemberID);
	}

	@RequestMapping(value = "/movieListing/listAllMovieReservations/{videoStoreMemberID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	List<MovieReservation> getmovieReservationsForMember(@PathVariable("videoStoreMemberID") String videoStoreMemberID) {
		return videoStoreMemberManager.getVideoStoreMembersReservations(videoStoreMemberID);
	}

	@RequestMapping(value = "movieListing/storeReservation/{reservationid}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void reserveMovie(@PathVariable("reservationid") String reservationID) {
		Authentication aut = SecurityContextHolder.getContext().getAuthentication();
		videoStoreMemberManager.reserveMovie(aut.getName(), reservationID, false);
	}

	@RequestMapping(value = "movieListing/cancelReservation/{movieID}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelMovie(@PathVariable("movieID") String movieID) {
		Authentication aut = SecurityContextHolder.getContext().getAuthentication();
		videoStoreMemberManager.cancelReservedMovie(aut.getName(), movieID);
	}

	@RequestMapping(value = "movieListing/reservationRented/{movieID}/memberid/{memeberid}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void movieRented(@PathVariable("movieID") String movieID, @PathVariable("memeberid") String memberid) {
		VideoStoreMember vsm = videoStoreMemberManager.getVideoStoreMemberByID(memberid);
		videoStoreMemberManager.rentedMovie(vsm, movieID);
	}
}
