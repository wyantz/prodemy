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
 * Nov 6, 2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	private String email;
	private String firstName;
	private String lastName;
}
