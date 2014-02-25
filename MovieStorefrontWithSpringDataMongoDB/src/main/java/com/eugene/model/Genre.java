package com.eugene.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Genre {

	@Id
	private String genreID;

	@Indexed(unique=true)
	private String genreName;

//	@DBRef
//	private List<Movie> movies;
	
	public Genre() {
		
	}
	
	public Genre(String genreName) {
		this.genreName = genreName;
	}

	// To stop JSON going nuts on recursion
//@JsonIgnore
//	public List<Movie> getMovies() {
//		return movies;
//	}
//
//	public void setMovies(List<Movie> movies) {
//		this.movies = movies;
//	}

	public String getGenreID() {
		return genreID;
	}

	public void setGenreID(String genreID) {
		this.genreID = genreID;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
}
