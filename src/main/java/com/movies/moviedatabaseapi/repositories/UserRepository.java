package com.movies.moviedatabaseapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movies.moviedatabaseapi.models.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	/**
	 * Returns boolean depending on email existence.
	 * 
	 * @param userEmail
	 * @return boolean
	 */
	boolean existsByUserEmail(String userEmail);
	
	/**
	 * Gets a user by supplied Email
	 *
	 * @param String containing Email
	 * @return The returned user object
	 */
	User getUserByUserEmail(String userEmail);
}
