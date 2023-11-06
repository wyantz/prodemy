/**
 * 
 */
package com.prodemy.boot.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

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
@Table(name = "t_book")
public class Book {
	@Id
	@Column("BOOKID")
	private Integer id;
	
	@Column("BOOKNM")
	private String title;
	
	@Column("BOOKAUTHOR")
	private String author;
	
	@Column("BOOKISBN")
	private String isbn;
}
