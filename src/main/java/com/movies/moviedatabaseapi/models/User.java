package com.movies.moviedatabaseapi.models;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "user")
public class User {
	
	/*
	 * user table entity.
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long user_id;
	
	@Column(unique = true, nullable = false)
	@Size(min=4,max=15)
	private String username;
	
	@Basic
	@NotNull
	@Size(min=12,max=25)
	private String password;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	//ElementCollection + CollectionTable will create and link to a new table, holding liked movies values
	@ElementCollection
	@CollectionTable(name = "liked_movies", joinColumns=@JoinColumn(name="user_id"))
	private Set<Long> liked_movies;
	
	@Basic
	@Temporal(TemporalType.DATE)
	@NotNull
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
