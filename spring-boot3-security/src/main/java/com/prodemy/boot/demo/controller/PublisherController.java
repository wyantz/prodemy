/**
 * 
 */
package com.prodemy.boot.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodemy.boot.demo.exception.PublisherAlreadyExistsException;
import com.prodemy.boot.demo.exception.PublisherNotFoundException;
import com.prodemy.boot.demo.model.Publisher;
import com.prodemy.boot.demo.model.response.HttpResponseModel;
import com.prodemy.boot.demo.repository.PublisherRepository;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @author Awiyanto Ajisasongko
 *
 * Nov 7, 2023
 */
@RestController
@RequestMapping("/publisher")
public class PublisherController {
	@Autowired private PublisherRepository repo;
	
	@GetMapping
	public HttpResponseModel<Iterable<Publisher>> findAll() {
		HttpResponseModel<Iterable<Publisher>> result = new HttpResponseModel<>();
		result.setStatus(0);
		result.setData(repo.findAll());
		return result;
	}
	
	@GetMapping("/{id}")
	public HttpResponseModel<Publisher> findById(@PathVariable("id") Integer id) {
		HttpResponseModel<Publisher> result = new HttpResponseModel<Publisher>();
		result.setStatus(0);
		result.setData(repo.findById(id).get());
		return result;
	}

	@DeleteMapping("/{id}")
	public HttpResponseModel<Publisher> deleteById(@PathVariable("id") Integer id) {
		HttpResponseModel<Publisher> result = new HttpResponseModel<Publisher>();
		result.setStatus(0);
		Optional<Publisher> op = repo.findById(id);
		if (op.isPresent()) {
			repo.deleteById(id);
		} else {
			throw new PublisherNotFoundException();
		}
		return result;
	}

	@Operation(summary = "Add or edit publisher", description = "Add or edit publisher, if id is null then will add new publisher")
	@PostMapping
	public HttpResponseModel<Publisher> save(@RequestBody Publisher publisher) {
		HttpResponseModel<Publisher> result = new HttpResponseModel<Publisher>();
		result.setStatus(0);
		
		if (publisher.getId()==null) {
			Publisher p = repo.findByName(publisher.getName());
			if (p==null) {
				p = new Publisher();
				p.setName(publisher.getName());
				result.setData(repo.save(p));
			} else {
				throw new PublisherAlreadyExistsException();
			}
		}
		result.setStatus(0);
		result.setData(repo.save(publisher));
		return result;
	}
}
