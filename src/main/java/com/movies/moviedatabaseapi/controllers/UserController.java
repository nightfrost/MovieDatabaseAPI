package com.movies.moviedatabaseapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.movies.moviedatabaseapi.models.User;
import com.movies.moviedatabaseapi.services.UserService;

@Controller
@RequestMapping(value = "api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers() {
		return userService.getUsers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
		return userService.getUserById(id);
	}
	
	@PostMapping()
	public ResponseEntity<User> addUser(@Validated @RequestBody User user) {
		return userService.addUser(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @Validated @RequestBody User user) {
		return userService.updateUser(id, user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id) {
		return userService.deleteUser(id);
	}
}
