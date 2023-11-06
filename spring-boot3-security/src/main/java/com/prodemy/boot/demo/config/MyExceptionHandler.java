/**
 * 
 */
package com.prodemy.boot.demo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.prodemy.boot.demo.exception.UserAlreadyExistsException;
import com.prodemy.boot.demo.model.response.HttpResponseModel;

/**
 * @author Awiyanto Ajisasongko
 *
 * Nov 3, 2023
 */
@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ BadCredentialsException.class })
    @ResponseBody
	public ResponseEntity<HttpResponseModel> handleBadCredentialException(Exception ex) {
		
		HttpResponseModel<Object> model = new HttpResponseModel<Object>();
		model.setStatus(-999); // Sesuaikan kode status disini tergantung error atau exceptionnya apa
		model.setMessage(ex.getMessage()); // Sesuaikan messagenya, disini sebagai contoh menggambil message dari exception

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(model);
	}

	@ExceptionHandler({ InsufficientAuthenticationException.class })
    @ResponseBody
	public ResponseEntity<HttpResponseModel> handleInsufficientAuthenticationException(Exception ex) {
		
		HttpResponseModel<Object> model = new HttpResponseModel<Object>();
		model.setStatus(-999); // Sesuaikan kode status disini tergantung error atau exceptionnya apa
		model.setMessage(ex.getMessage()); // Sesuaikan messagenya, disini sebagai contoh menggambil message dari exception

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(model);
	}
	
	@ExceptionHandler({ UserAlreadyExistsException.class })
    @ResponseBody
	public ResponseEntity<HttpResponseModel> userAlreadyExistsException(Exception ex) {
		
		HttpResponseModel<Object> model = new HttpResponseModel<Object>();
		model.setStatus(-999); // Sesuaikan kode status disini tergantung error atau exceptionnya apa
		model.setMessage(ex.getMessage()); // Sesuaikan messagenya, disini sebagai contoh menggambil message dari exception

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
	}
}
