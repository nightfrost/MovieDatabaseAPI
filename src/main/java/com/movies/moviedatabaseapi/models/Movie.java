package com.movies.moviedatabaseapi.models;

import java.util.Set;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "movie")
public class Movie {
	
	/*
	 * movie table entity.
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long movie_id;
	
	@Column(unique = true, nullable = false)
	private String movieTitle;
	
	@Basic
	private Long runtime;
	
	//ElementCollection + CollectionTable will create and link to a new table, holding genre values
	@ElementCollection
	@CollectionTable(name = "genres", joinColumns=@JoinColumn(name="movie_id"))
	private Set<String> genre;
	
	//ElementCollection as well???
	@Basic
	private String director;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date release_date;
	
	@Basic
	private Double imdb_rating;
	
	@Basic
	private String magnet_uri;
	
	@Basic
	private String poster_uri;
	
	@Basic
	private String trailer_uri;

	/*
	 * Getters & Setters.
	 */
	
	public Long getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public Long getRuntime() {
		return runtime;
	}

	public void setRuntime(Long runtime) {
		this.runtime = runtime;
	}

	public Set<String> getGenre() {
		return genre;
	}

	public void setGenre(Set<String> genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public Double getImdb_rating() {
		return imdb_rating;
	}

	public void setImdb_rating(Double imdb_rating) {
		this.imdb_rating = imdb_rating;
	}

	public String getMagnet_uri() {
		return magnet_uri;
	}

	public void setMagnet_uri(String magnet_uri) {
		this.magnet_uri = magnet_uri;
	}

	public String getPoster_uri() {
		return poster_uri;
	}

	public void setPoster_uri(String poster_uri) {
		this.poster_uri = poster_uri;
	}

	public String getTrailer_uri() {
		return trailer_uri;
	}

	public void setTrailer_uri(String trailer_uri) {
		this.trailer_uri = trailer_uri;
	}
}
