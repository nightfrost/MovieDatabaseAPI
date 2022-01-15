package com.movies.moviedatabaseapi.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;
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
	
	public ResponseEntity<List<User>> getUsers() {
		List<User> usersList = null;
		HttpStatus status = null;
		if (!(usersList = userRepository.findAll()).isEmpty()) {
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(usersList, status);
	}
	
	public ResponseEntity<User> getUserById(Long id) {
		User returnUser = null;
		HttpStatus status = null;
		if (userRepository.existsById(id) &&
				(returnUser = userRepository.getById(id)) != null) {
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(returnUser, status);
	}
	
	
	@SuppressWarnings("null")
	public ResponseEntity<User> addUser(User newUser) {
		User returnUser = null;
		HttpStatus status = null;
		
		if ((returnUser = userRepository.getUserByUserEmail(newUser.getEmail())) == null) {
			if (newUser.getEmail().contains("@") && HelperController.passwordContainsSpecialCharacters(newUser.getPassword())) {
				BeanUtils.copyProperties(newUser, returnUser);
				userRepository.saveAndFlush(returnUser);
				returnUser.setPassword("Redacted.");
				status = HttpStatus.OK;
			}
		} else {
			returnUser.setPassword("Redacted.");
			status = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<>(returnUser, status);
	}
	
	public ResponseEntity<User> updateUser(User newUser) {
		User returnUser = null;
		HttpStatus status = null;
		if (userRepository.existsByUserEmail(newUser.getEmail())) {
			User tmpUser = userRepository.getUserByUserEmail(newUser.getEmail());
			try {
				BeanUtils.copyProperties(tmpUser, newUser);
				returnUser = userRepository.saveAndFlush(tmpUser);
				status = HttpStatus.OK;
			} catch (BeansException e) {
				e.printStackTrace();
			}
		} else {
			returnUser = newUser;
			returnUser.setPassword("redacted");
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(returnUser, status);
	}
 }