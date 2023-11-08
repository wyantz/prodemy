/**
 * 
 */
package com.prodemy.boot.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodemy.boot.demo.dto.BookDto;
import com.prodemy.boot.demo.exception.BookNotFoundException;
import com.prodemy.boot.demo.model.Book;
import com.prodemy.boot.demo.model.Publisher;
import com.prodemy.boot.demo.model.request.AddBookRequest;
import com.prodemy.boot.demo.model.response.HttpResponseModel;
import com.prodemy.boot.demo.repository.BookRepository;
import com.prodemy.boot.demo.repository.PublisherRepository;
import com.prodemy.boot.demo.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
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
	@Autowired private BookService service;
	@Autowired private PublisherRepository publisherRepository;
	
	@Operation(summary = "Add new book", description = "Adding new book")
	@PostMapping
	public HttpResponseModel<BookDto> addBook(@RequestBody AddBookRequest req) {
		HttpResponseModel<BookDto> resp = new HttpResponseModel<>();
		
		Book entity = Book.builder()
				.author(req.getAuthor())
				.title(req.getTitle())
				.isbn(req.getIsbn())
				.publisherId(req.getPublisherId())
				.build();
		
		resp.setStatus(0);
		resp.setData(convertToDto(this.service.saveBook(entity)));
		
		return resp;
	}
	
	@Operation(summary = "List all books", description = "Get the list of all books")
	@PermitAll
	@GetMapping
	public HttpResponseModel<List<BookDto>> findAll() {
		HttpResponseModel<List<BookDto>> resp = new HttpResponseModel<>();

		List<BookDto> l = StreamSupport.stream(repo.findAll().spliterator(), true)
				.map(this::convertToDto).collect(Collectors.toList());
		
		resp.setStatus(0);
		resp.setData(l);
		
		return resp;
	}

	@Operation(summary = "Get book by id", description = "Get book by its identifier (id)")
	@GetMapping(value = "/{id}")
	public HttpResponseModel<BookDto> findBook(@PathVariable("id") Integer id) {
		HttpResponseModel<BookDto> resp = new HttpResponseModel<>();
		
		Optional<Book> op = this.repo.findById(id);
		if (op.isPresent()) {
			resp.setStatus(0);
			resp.setData(convertToDto(op.get()));
		} else {
			resp.setStatus(-1);
			resp.setData(null);
			
		}
		return resp;
	}

	@Operation(summary = "Delete a book", description = "Delete a book by id")
	@DeleteMapping("/{id}")
	public HttpResponseModel<Book> deleteBook(@PathVariable("id") Integer id) {
		HttpResponseModel<Book> resp = new HttpResponseModel<>();
		
		Optional<Book> op = this.repo.findById(id);
		if (op.isPresent()) {
			repo.deleteById(id);
			resp.setStatus(0);
		} else {
			throw new BookNotFoundException();
		}
		return resp;
	}

	@Operation(summary = "Update book", description = "Modify or update the book")
	@PutMapping
	public HttpResponseModel<BookDto> updateBook(@RequestBody Book req) {
		HttpResponseModel<BookDto> resp = new HttpResponseModel<>();
		
		Book entity = Book.builder()
				.id(req.getId())
				.author(req.getAuthor())
				.title(req.getTitle())
				.isbn(req.getIsbn())
				.publisherId(req.getPublisherId())
				.build();
		
		resp.setStatus(0);
		resp.setData(convertToDto(this.repo.save(entity)));
		
		return resp;
	}
	
	private BookDto convertToDto(Book entity) {
		Optional<Publisher> op = null;
		String publisherName = null;
		if (entity.getPublisherId()!=null) {
			op = publisherRepository.findById(entity.getPublisherId());
			if (op.isPresent()) {
				publisherName = op.get().getName();
			}
		}
		
		return BookDto.builder()
				.id(entity.getId())
				.author(entity.getAuthor())
				.title(entity.getTitle())
				.isbn(entity.getIsbn())
				.publisher(publisherName)
				.build();
	}
}
