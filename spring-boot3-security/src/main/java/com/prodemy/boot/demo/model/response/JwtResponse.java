/**
 * 
 */
package com.prodemy.boot.demo.model.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
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
public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -852475155378971433L;
	private String token;
}
