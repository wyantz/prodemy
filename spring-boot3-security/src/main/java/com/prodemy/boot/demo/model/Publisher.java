/**
 * 
 */
package com.prodemy.boot.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
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
@Table(name = "t_publisher")
public class Publisher {
	@Id
	@Column("PUBID")
	private Integer id;
	
	@Column("PUBNM")
	private String name;
}
