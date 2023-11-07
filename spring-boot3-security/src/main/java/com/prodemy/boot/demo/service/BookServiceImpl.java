/**
 * 
 */
package com.prodemy.boot.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prodemy.boot.demo.exception.PublisherNotFoundException;
import com.prodemy.boot.demo.model.Book;
import com.prodemy.boot.demo.model.Publisher;
import com.prodemy.boot.demo.repository.BookRepository;
import com.prodemy.boot.demo.repository.PublisherRepository;

/**
 * @author Awiyanto Ajisasongko
 *
 * Nov 7, 2023
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {
	@Autowired private BookRepository bookRepository;
	@Autowired private PublisherRepository publisherRepository;

	@Override
	public Book saveBook(Book book) {
		if (book.getPublisherId()!=null) {
			Optional<Publisher> op = publisherRepository.findById(book.getPublisherId());
			if (!op.isPresent()) throw new PublisherNotFoundException();
		}
		return bookRepository.save(book);
	}

}
