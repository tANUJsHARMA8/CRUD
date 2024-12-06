package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Data does not present");
	}
	
	
	public ResourceNotFoundException(String msg) {
		
		super(msg);
		
		
	}
	

}
