/**
 * 
 */
package com.prodemy.boot.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodemy.boot.demo.model.Book;

/**
 * @author Awiyanto Ajisasongko
 *
 * Feb 7, 2023
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

}
