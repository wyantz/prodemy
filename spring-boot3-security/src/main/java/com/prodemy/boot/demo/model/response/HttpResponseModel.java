/**
 * 
 */
package com.prodemy.boot.demo.model.response;

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
public class HttpResponseModel<D> {
	private int status;
	private String message;
	private D data;
}
