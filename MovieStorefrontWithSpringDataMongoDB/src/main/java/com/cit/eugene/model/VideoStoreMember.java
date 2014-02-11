package com.cit.eugene.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VideoStoreMember {

	@Id
	private String videoStoreMemberID;

	private String name;

	private String location;

	@Indexed(unique=true)
	private String memebershipNumber;

	@DBRef
	private User user;

	@DBRef
	private Account account;

	@DBRef
	private List<MovieReservation> movieReservations;

	public String getVideoStoreMemberID() {
		return videoStoreMemberID;
	}

	public void setVideoStoreMemberID(String videoStoreMemberID) {
		this.videoStoreMemberID = videoStoreMemberID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMemebershipNumber() {
		return memebershipNumber;
	}

	public void setMemebershipNumber(String memebershipNumber) {
		this.memebershipNumber = memebershipNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	// To stop JSON going nuts on recursion
	@JsonIgnore
	public List<MovieReservation> getMovieReservations() {
		return movieReservations;
	}

	public void setMovieReservations(List<MovieReservation> movieReservations) {
		this.movieReservations = movieReservations;
	}
}
