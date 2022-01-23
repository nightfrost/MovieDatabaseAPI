package com.movies.moviedatabaseapi.models;

import java.util.Set;

import javax.persistence.*;


import java.util.Date;

@Entity
@Table(name = "_user")
public class User {
	
	/*
	 * user table entity.
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long user_id;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Basic
	private String password;
	
	@Column(unique = true, nullable = false)
	private String userEmail;
	
	//ElementCollection + CollectionTable will create and link to a new table, holding liked movies values
	@ElementCollection
	@CollectionTable(name = "liked_movies", joinColumns=@JoinColumn(name="user_id"))
	private Set<Long> liked_movies;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date created_at;

	/*
	 * Getters & Setters.
	 */

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Set<Long> getLiked_movies() {
		return liked_movies;
	}

	public void setLiked_movies(Set<Long> liked_movies) {
		this.liked_movies = liked_movies;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
}
