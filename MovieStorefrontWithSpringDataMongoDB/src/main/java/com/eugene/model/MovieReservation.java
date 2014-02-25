package com.eugene.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MovieReservation {

	@Id
	private String movieReservationID;

	private Boolean rented = false;

	private Date reservationDate;

	private VideoStoreMember memberID;

	@DBRef
	private Movie movie;

	public String getMovieReservationID() {
		return movieReservationID;
	}

	public void setMovieReservationID(String movieReservationID) {
		this.movieReservationID = movieReservationID;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public VideoStoreMember getMemberID() {
		return memberID;
	}

	public void setMemberID(VideoStoreMember memberID) {
		this.memberID = memberID;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Boolean getRented() {
		return rented;
	}

	public void setRented(Boolean rented) {
		this.rented = rented;
	}
}
