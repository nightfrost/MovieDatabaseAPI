package com.movies.moviedatabaseapi.services;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.movies.moviedatabaseapi.models.User;
import com.movies.moviedatabaseapi.repositories.UserRepository;

import com.movies.moviedatabaseapi.controllers.HelperController;

@Service
public class UserService {
	
	//UserRepository dependency
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 
	 * Get all users.
	 * 
	 * @return ResponseEntity
	 */
	public ResponseEntity<List<User>> getUsers() {
		List<User> usersList = null;
		HttpStatus status = null;
		try {
			if (!(usersList = userRepository.findAll()).isEmpty()) {
				usersList.forEach((n) -> n.setPassword("Redacted."));
				status = HttpStatus.OK;
			} else {
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			System.out.println("The house is on fire...");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(usersList, status);
	}
	
	/**
	 * 
	 * Get user by id
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	public ResponseEntity<User> getUserById(Long id) {
		User returnUser = null;
		HttpStatus status = null;
		try {
			if ((returnUser = userRepository.findById(id).get()) != null) {
				returnUser.setPassword("Redacted.");
				status = HttpStatus.OK;
			} else {
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			System.out.println("The house is on fire...");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnUser, status);
	}
	
	/**
	 * 
	 * Add user.
	 * 
	 * @param newUser
	 * @return ResponseEntity
	 */
	public ResponseEntity<User> addUser(User newUser) {
		User returnUser = null;
		HttpStatus status = null;
		try {
			if ((returnUser = userRepository.getUserByUserEmail(newUser.getUserEmail())) == null) {
				//Password checks.... Yonkers, find something else?
				if (newUser.getUserEmail().contains("@") && HelperController.passwordContainsSpecialCharacters(newUser.getPassword())) {
					returnUser = userRepository.saveAndFlush(newUser);
					returnUser.setPassword("Redacted.");
					status = HttpStatus.OK;
				} else {
					status = HttpStatus.BAD_REQUEST;
				}
			} else {
				status = HttpStatus.CONFLICT;
			}
		} catch (BeansException e) {
			System.out.printf("Failed to copy values into user object...\nPrinting message...");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("The house is on fire...");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnUser, status);
	}
	
	/**
	 * 
	 * Update user.
	 * 
	 * @param newUser
	 * @return ResponseEntity
	 */
	public ResponseEntity<User> updateUser(Long id, User newUser) {
		User returnUser = null;
		HttpStatus status = null;
		try {
			if ((returnUser = userRepository.findById(id).get()) != null) {
				newUser = (User) HelperService.partialUpdate(returnUser, newUser);
				returnUser = userRepository.saveAndFlush(newUser);
				returnUser.setPassword("Redacted.");
				status = HttpStatus.OK;
			} else {
				status = HttpStatus.NOT_FOUND;
			}
		} catch (BeansException e) {
			System.out.printf("Failed to copy values into user object...\nPrinting message...");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("The house is on fire...");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnUser, status);
	}
	
	/**
	 * 
	 * Deletes user with given ID.
	 * 
	 * @param id
	 * @return deleted User
	 */
	public ResponseEntity<User> deleteUser(Long id) {
		User returnUser = null;
		HttpStatus httpStatus = null;
		try {
			if ((returnUser = userRepository.findById(id).get()) != null) {
				userRepository.deleteById(id);
				httpStatus = HttpStatus.OK;
			} else {
				httpStatus = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			System.out.println("The house is on fire...");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<User>(returnUser, httpStatus);
	}
 }