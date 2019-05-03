package fr.epita.quiz.services;

import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author Faisal 
 * This is for authenticating user
 * 
 */

@Repository
public class Authentication_Service {
	/**
	 * 
	 * @param login
	 * @param password
	 * @return
	 */
	public boolean authenticate(String login, String password) {
		// TODO get a real authentication later
		return true;
	}

}
