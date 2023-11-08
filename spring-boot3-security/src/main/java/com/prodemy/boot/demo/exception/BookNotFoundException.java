/**
 * 
 */
package com.prodemy.boot.demo.exception;

/**
 * @author Awiyanto Ajisasongko
 *
 * Nov 7, 2023
 */
public class BookNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2464183406461797012L;

	public BookNotFoundException() {
		super("Book not found");
	}
}
