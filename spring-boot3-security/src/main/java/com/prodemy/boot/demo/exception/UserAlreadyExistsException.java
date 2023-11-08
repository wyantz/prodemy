/**
 * 
 */
package com.prodemy.boot.demo.exception;

/**
 * @author Awiyanto Ajisasongko
 *
 * Nov 6, 2023
 */
public class UserAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 501735311336090223L;

	public UserAlreadyExistsException(String user) {
		super("User "+user+" already exists");
	}
}
