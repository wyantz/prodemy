/**
 * 
 */
package com.prodemy.boot.demo.exception;

/**
 * @author Awiyanto Ajisasongko
 *
 * Nov 7, 2023
 */
public class PublisherNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -2504357656421954162L;

	public PublisherNotFoundException() {
		super("Publisher not found");
	}
}
