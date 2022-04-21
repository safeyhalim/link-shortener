package org.shalim.shortlink.presenter.api.rest.common;

import org.shalim.shortlink.core.domain.exceptions.DomainException;
import org.shalim.shortlink.core.domain.exceptions.InvalidUrlException;
import org.shalim.shortlink.core.domain.exceptions.UrlNotFoundException;
import org.shalim.shortlink.presenter.api.rest.responses.BasicApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = DomainException.class)
	ResponseEntity<BasicApiResponse> handleDomainException(DomainException e) {
		return new ResponseEntity<>(new BasicApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = InvalidUrlException.class)
	ResponseEntity<BasicApiResponse> handleInvalidUrlException(InvalidUrlException e) {
		return new ResponseEntity<>(new BasicApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = UrlNotFoundException.class)
	ResponseEntity<BasicApiResponse> handleUrlNotFoundException(UrlNotFoundException e) {
		return new ResponseEntity<>(new BasicApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return new ResponseEntity<>(new BasicApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
	}
}
