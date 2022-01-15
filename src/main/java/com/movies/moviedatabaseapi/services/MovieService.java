package com.movies.moviedatabaseapi.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
	
	public ResponseEntity<List<Movie>> getMovies() {
		List<Movie> moviesList = null;
		HttpStatus httpStatus = null;
		if (!(moviesList = movieRepository.findAll()).isEmpty()) {
			httpStatus = HttpStatus.OK;
		} else {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<Movie>>(moviesList, httpStatus);
	}
	
	public ResponseEntity<Movie> getMovieById(Long id) {
		Movie returnMovie = null;
		HttpStatus httpStatus = null;
		if (movieRepository.existsById(id) && (returnMovie = movieRepository.getById(id)) != null) {
			httpStatus = HttpStatus.OK;
		} else {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<Movie>(returnMovie, httpStatus);
	}
	
	public ResponseEntity<Movie> addMovie(Movie newMovie) {
		Movie returnMovie = null;
		HttpStatus httpStatus = null;
		if (movieRepository.existsByMovieTitle(newMovie.getTitle()) && (returnMovie = movieRepository.saveAndFlush(newMovie)) != null) {
			httpStatus = HttpStatus.CREATED;
		} else {
			httpStatus = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<Movie>(returnMovie, httpStatus);
	}
	
	public ResponseEntity<Movie> updateMovie(Movie newMovie) {
		Movie returnMovie = null;
		HttpStatus httpStatus = null;
		if (movieRepository.existsByMovieTitle(newMovie.getTitle())
				&& (returnMovie = movieRepository.getById(newMovie.getMovie_id())) != null) {
			try {
				BeanUtils.copyProperties(newMovie, returnMovie);
				movieRepository.saveAndFlush(returnMovie);
				httpStatus = HttpStatus.OK;
			} catch (BeansException e) {
				System.out.printf("Failed to copy values into movie object...\nPrinting message...");
				System.out.println(e.getMessage());
			}
		} else {
			httpStatus = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<>(returnMovie, httpStatus);
	}
	
	public ResponseEntity<Movie> deleteMovie(Long id) {
		Movie returnMovie = null;
		HttpStatus httpStatus = null;
		if (movieRepository.existsById(id)) {
			try {
				returnMovie = movieRepository.getById(id);
				movieRepository.deleteById(id);
				httpStatus = HttpStatus.OK;
			} catch (IllegalArgumentException e) {
				System.out.println("That's weird... Supply a number, please.");
				System.out.println(e.getMessage());
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} else {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(returnMovie, httpStatus);
	}
}
