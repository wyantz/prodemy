/**
 * 
 */
package com.prodemy.boot.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodemy.boot.demo.model.User;

/**
 * @author Awiyanto Ajisasongko
 *
 * Feb 7, 2023
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByEmail(String email);
}
