/**
 * 
 */
package com.prodemy.boot.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodemy.boot.demo.model.Role;

/**
 * @author Awiyanto Ajisasongko
 *
 * Feb 7, 2023
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

}
