/**
 * 
 */
package com.prodemy.boot.demo.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Awiyanto Ajisasongko
 *
 * Feb 7, 2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddBookRequest {
	private String title;
	private String author;
	private String isbn;
	private Integer publisherId;
}
