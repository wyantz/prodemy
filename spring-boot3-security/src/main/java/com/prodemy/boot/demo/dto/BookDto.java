/**
 * 
 */
package com.prodemy.boot.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Awiyanto Ajisasongko
 *
 * Nov 7, 2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
	private Integer id;
	private String title;
	private String author;
	private String isbn;
	private String publisher;
}
