package com.cit.eugene.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Movie {

	@Id
	private String movieID;

	private Integer rating;

	private Double price;

	private String displayRunTime;

	private Integer year;

	private String title;

	private String summary;

	private String posterFileName;

	private String actorsDisplay;

	private String directorsDisplay;

	private String producersDisplay;

	private String studioDisplay;

	private transient boolean rented = false;

	@DBRef
	private List<Genre> genres;

	public String getMovieID() {
		return movieID;
	}

	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDisplayRunTime() {
		return displayRunTime;
	}

	public void setDisplayRunTime(String displayRunTime) {
		this.displayRunTime = displayRunTime;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getActorsDisplay() {
		return actorsDisplay;
	}

	public void setActorsDisplay(String actorsDisplay) {
		this.actorsDisplay = actorsDisplay;
	}

	public String getDirectorsDisplay() {
		return directorsDisplay;
	}

	public void setDirectorsDisplay(String directorsDisplay) {
		this.directorsDisplay = directorsDisplay;
	}

	public String getProducersDisplay() {
		return producersDisplay;
	}

	public void setProducersDisplay(String producersDisplay) {
		this.producersDisplay = producersDisplay;
	}

	public String getPosterFileName() {
		return posterFileName;
	}

	public void setPosterFileName(String posterFileName) {
		this.posterFileName = posterFileName;
	}

	public String getStudioDisplay() {
		return studioDisplay;
	}

	public void setStudioDisplay(String studioDisplay) {
		this.studioDisplay = studioDisplay;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

}
