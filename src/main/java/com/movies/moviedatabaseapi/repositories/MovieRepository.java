package com.movies.moviedatabaseapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movies.moviedatabaseapi.models.*;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	/**
	 * 
	 * Returns boolean depending on title existence.
	 * 
	 * @param movieTitle
	 * @return boolean
	 */
	boolean existsByMovieTitle(String movieTitle);

	/**
	 * 
	 * Gets a given movie object by title.
	 * 
	 * @param movieTitle
	 * @return Movie
	 */
	Movie getMovieByMovieTitle(String movieTitle);
}
