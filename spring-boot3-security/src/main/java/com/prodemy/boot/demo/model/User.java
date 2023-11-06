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
@Table(name = "t_user")
public class User {
	@Id
	private Integer id;
	
	@Column("EMAIL")
	private String email;
	
	@Column("FIRSTNAME")
	private String firstName;
	
	@Column("LASTNAME")
	private String lastName;

	@Column("PASSWORD")
	private String password;
}
