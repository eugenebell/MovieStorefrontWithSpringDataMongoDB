package com.eugene.service.business;

import java.util.List;

import com.eugene.model.MovieReservation;
import com.eugene.model.VideoStoreMember;

/**
 * Class is for the Management of VidepStoreMember.
 * 
 * @author Eugene Bell
 */
public interface VideoStoreMemberService {

	/**
	 * Returns all the VideoStoreMembers.
	 * 
	 * @return List<VideoStoreMember>
	 */
	public Iterable<VideoStoreMember> getAllVideoStoreMember();
	
	/**
	 * Returns VideoStoreMember based on name.
	 * 
	 * @param videoStoreMemberName
	 * @return VideoStoreMember
	 */
	public VideoStoreMember getVideoStoreMember(String videoStoreMemberName);
	
	/**
	 * Returns VideoStoreMember based on ID.
	 * 
	 * @param videoStoreMemberID
	 * @return VideoStoreMember
	 */
	public VideoStoreMember getVideoStoreMemberByID(String videoStoreMemberID);
	
	/**
	 * Delete VideoStoreMember by ID.
	 * 
	 * @param videoStoreMemberID
	 */
	public void deleteVideoStoreMember(String videoStoreMemberID);
	
	/**
	 * Returns List of MovieReservation based on VideoStoreMember ID.
	 * 
	 * @param videoStoreMemberID
	 * @return List<MovieReservation>
	 */
	public List<MovieReservation> getVideoStoreMembersReservations(String videoStoreMemberID);
	
	/**
	 * Store MovieReservation
	 * 
	 * @param videoStoreMember
	 * @return VideoStoreMember the store version.
	 */
	public VideoStoreMember storeVideoStoreMember(VideoStoreMember videoStoreMember);
	
	/**
	 * Reserve the movie based on user, movie id.
	 * 
	 * @param username
	 * @param movieID
	 * @param rented
	 * @return boolean if it has succeeded
	 */
	public boolean reserveMovie(String username, String movieID, boolean rented);
	
	/**
	 * Cancel the reserved Movie.
	 *  
	 * @param username
	 * @param reservationID
	 * @return boolean if it has succeeded
	 */
	public boolean cancelReservedMovie(String username, String reservationID);
	
	/**
	 * Movie has been rented.
	 * 
	 * @param vsm
	 * @param reservationID
	 * @return boolean if it has succeeded
	 */
	public boolean rentedMovie(VideoStoreMember vsm, String reservationID);
}
