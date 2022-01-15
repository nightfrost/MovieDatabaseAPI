package com.movies.moviedatabaseapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.movies.moviedatabaseapi.models.User;
import com.movies.moviedatabaseapi.services.UserService;

import static com.movies.moviedatabaseapi.controllers.HelperController.BASE_URI_V1;

@RestController
@RequestMapping(value = BASE_URI_V1 + "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return userService.getUsers();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
		return userService.getUserById(id);
	}

}
