/**
 * 
 */
package com.prodemy.boot.demo.controller;

import java.util.Optional;

import org.springdoc.core.annotations.RouterOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodemy.boot.demo.dto.UserDto;
import com.prodemy.boot.demo.exception.UserAlreadyExistsException;
import com.prodemy.boot.demo.model.User;
import com.prodemy.boot.demo.model.request.RegistrationRequest;
import com.prodemy.boot.demo.model.response.HttpResponseModel;
import com.prodemy.boot.demo.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * @author Awiyanto Ajisasongko
 *
 * Nov 6, 2023
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired private UserRepository repo;
	@Autowired PasswordEncoder encoder;
	
	@Operation(summary = "Register new user", description = "Registering for new user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull registration"),
			@ApiResponse(responseCode = "400", description = "Status code 4001 in the response means registration failed")
	})
	@PostMapping("/register")
	public HttpResponseModel<UserDto> register(@RequestBody RegistrationRequest req) {
		HttpResponseModel<UserDto> result = new HttpResponseModel<UserDto>();
		User user = repo.findByEmail(req.getEmail());
		if (user==null) {
			// User belum ada, lanjutkan
			repo.save(User.builder()
					.email(req.getEmail())
					.firstName(req.getFirstName())
					.lastName(req.getLastName())
					.password(encoder.encode(req.getPassword()))
					.build());
			
			UserDto resp = UserDto.builder()
					.email(req.getEmail())
					.firstName(req.getFirstName())
					.lastName(req.getLastName())
					.build();
			
			result.setStatus(0);
			result.setMessage(null);
			result.setData(resp);
		} else {
			// User sudah ada, throw Exception
			throw new UserAlreadyExistsException(req.getEmail());
		}
		return result;
	}
}
