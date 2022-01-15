package com.movies.moviedatabaseapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movies.moviedatabaseapi.models.*;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	boolean existsByMovieTitle(String movieTitle);
	Movie getMovieByMovieTitle(String movieTitle);
}
