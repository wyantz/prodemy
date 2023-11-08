/**
 * 
 */
package com.prodemy.boot.demo.exception;

/**
 * @author Awiyanto Ajisasongko
 *
 * Nov 7, 2023
 */
public class PublisherAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = -4200212162395126948L;

	public PublisherAlreadyExistsException() {
		super("Publisher already exists");
	}
}
