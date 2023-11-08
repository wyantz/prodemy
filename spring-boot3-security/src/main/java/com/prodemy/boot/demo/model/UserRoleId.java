/**
 * 
 */
package com.prodemy.boot.demo.model;

import org.springframework.data.relational.core.mapping.Column;

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
public class UserRoleId {
	@Column("EMAIL")
	private String userId;
	
	@Column("ROLEID")
	private String roleId;
}
