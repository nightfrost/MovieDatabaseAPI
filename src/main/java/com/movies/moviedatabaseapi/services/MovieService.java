package com.movies.moviedatabaseapi.services;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.movies.moviedatabaseapi.models.Movie;
import com.movies.moviedatabaseapi.repositories.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	/**
	 * 
	 * Get all movies.
	 * 
	 * @return ResponseEntity
	 */
	public ResponseEntity<List<Movie>> getMovies() {
		List<Movie> moviesList = null;
		HttpStatus httpStatus = null;
		try {
			if (!(moviesList = movieRepository.findAll()).isEmpty()) {
				httpStatus = HttpStatus.OK;
			} else {
				httpStatus = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			System.out.println("The house is on fire...");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<List<Movie>>(moviesList, httpStatus);
	}
	
	/**
	 * 
	 * Get Movie by id.
	 * 
	 * @param id
	 * @return ReponsEntity
	 */
	public ResponseEntity<Movie> getMovieById(Long id) {
		Movie returnMovie = null;
		HttpStatus httpStatus = null;
		try {
			if (id != null && movieRepository.existsById(id) 
					&& (returnMovie = movieRepository.findById(id).get()) != null) {
				httpStatus = HttpStatus.OK;
			} else {
				httpStatus = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			System.out.println("The house is on fire...");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<Movie>(returnMovie, httpStatus);
	}
	
	/**
	 * 
	 * Add Movie
	 * 
	 * @param newMovie
	 * @return ResponseEntity
	 */
	public ResponseEntity<Movie> addMovie(Movie newMovie) {
		Movie returnMovie = null;
		HttpStatus httpStatus = null;
		try {
			if (!movieRepository.existsByMovieTitle(newMovie.getMovieTitle()) 
					&& (returnMovie = movieRepository.saveAndFlush(newMovie)) != null) {
				httpStatus = HttpStatus.CREATED;
			} else {
				httpStatus = HttpStatus.CONFLICT;
			}
		} catch (Exception e) {
			System.out.println("The house is on fire...");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<Movie>(returnMovie, httpStatus);
	}
	
	/**
	 * 
	 * Update Movie.
	 * 
	 * @param newMovie
	 * @return ResponseEntity
	 */
	public ResponseEntity<Movie> updateMovie(Long id, Movie newMovie) {
		Movie returnMovie = null;
		HttpStatus httpStatus = null;
		try {
			if ((returnMovie = movieRepository.findById(id).get()) != null) {
				newMovie = (Movie) HelperService.partialUpdate(returnMovie, newMovie);
				returnMovie = movieRepository.saveAndFlush(newMovie);
				httpStatus = HttpStatus.OK;
			} else {
				httpStatus = HttpStatus.CONFLICT;
			}
		} catch (BeansException e) {
			System.out.printf("Failed to copy values into movie object...\nPrinting message...");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("The house is on fire...");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnMovie, httpStatus);
	}
	
	/**
	 * 
	 * Delete movie.
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	public ResponseEntity<Movie> deleteMovie(Long id) {
		Movie returnMovie = null;
		HttpStatus httpStatus = null;
		try {
			if ((returnMovie = movieRepository.findById(id).get()) != null) {
				movieRepository.deleteById(id);
				httpStatus = HttpStatus.OK;
			} else {
				httpStatus = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			System.out.println("The house is on fire...");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnMovie, httpStatus);
	}
}
