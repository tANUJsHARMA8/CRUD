package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleGlobalException(ResourceNotFoundException ex){
		
		Map map = new HashMap();
		
		map.put("Message", ex.getMessage());
		map.put("Success", false);
		map.put("Status", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
		
		
	}

}
