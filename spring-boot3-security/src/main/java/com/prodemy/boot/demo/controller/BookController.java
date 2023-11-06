/**
 * 
 */
package com.prodemy.boot.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodemy.boot.demo.model.Book;
import com.prodemy.boot.demo.model.request.AddBookRequest;
import com.prodemy.boot.demo.model.response.HttpResponseModel;
import com.prodemy.boot.demo.repository.BookRepository;

import jakarta.annotation.security.PermitAll;

/**
 * @author Awiyanto Ajisasongko
 *
 * Feb 7, 2023
 */
@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired private BookRepository repo;
	
	@PostMapping
	public HttpResponseModel<Book> addBook(@RequestBody AddBookRequest req) {
		HttpResponseModel<Book> resp = new HttpResponseModel<>();
		
		Book entity = Book.builder()
				.author(req.getAuthor())
				.title(req.getTitle())
				.isbn(req.getIsbn())
				.build();
		
		resp.setStatus(0);
		resp.setData(this.repo.save(entity));
		
		return resp;
	}
	
	@PermitAll
	@GetMapping
	public HttpResponseModel<Iterable<Book>> findAll() {
		HttpResponseModel<Iterable<Book>> resp = new HttpResponseModel<>();

		resp.setStatus(0);
		resp.setData(this.repo.findAll());
		
		return resp;
	}
	
	@GetMapping(value = "/{id}")
	public HttpResponseModel<Book> findBook(@PathVariable("id") Integer id) {
		HttpResponseModel<Book> resp = new HttpResponseModel<>();
		
		Optional<Book> op = this.repo.findById(id);
		if (op.isPresent()) {
			resp.setStatus(0);
			resp.setData(op.get());
		} else {
			resp.setStatus(-1);
			resp.setData(null);
			
		}
		return resp;
	}

	@PutMapping
	public HttpResponseModel<Book> updateBook(@RequestBody Book req) {
		HttpResponseModel<Book> resp = new HttpResponseModel<>();
		
		Book entity = Book.builder()
				.id(req.getId())
				.author(req.getAuthor())
				.title(req.getTitle())
				.isbn(req.getIsbn())
				.build();
		
		resp.setStatus(0);
		resp.setData(this.repo.save(entity));
		
		return resp;
	}
}
