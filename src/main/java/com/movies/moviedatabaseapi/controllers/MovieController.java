package com.movies.moviedatabaseapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.movies.moviedatabaseapi.models.Movie;
import com.movies.moviedatabaseapi.services.MovieService;

import static com.movies.moviedatabaseapi.controllers.HelperController.BASE_URI_V1;

@RestController
@RequestMapping(value = BASE_URI_V1 + "/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping()
	public ResponseEntity<List<Movie>> getAllMovies() {
		return movieService.getMovies();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable(value = "id") Long id) {
		return movieService.getMovieById(id);
	}
	
	@PostMapping
	public ResponseEntity<Movie> addMovie(@Validated @RequestBody Movie movie) {
		return movieService.addMovie(movie);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Movie> updateMovie(@Validated @RequestBody Movie movie) {
		return movieService.updateMovie(movie);
	}
	
}
