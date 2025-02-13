package com.lr0cha.park_api.web.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorMessage {
	private String path;
	private String method;
	private Integer status;
	private String statusText;
	private String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String, String> errors;
	
	public ErrorMessage(HttpServletRequest request, HttpStatus status, String message){
		this.path = request.getRequestURI();
		this.method = request.getMethod();
		this.status = status.value();
		this.statusText = status.getReasonPhrase();
		this.message = message;
	}
	// também adiciona erros de validação
	public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult result) {
		this(request, status, message);
		addErrors(result);
	}

	private void addErrors(BindingResult result) {
		this.errors = new HashMap<>();
		for(FieldError fieldError : result.getFieldErrors()) {
			this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
	}
	
}
