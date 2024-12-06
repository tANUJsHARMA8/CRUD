package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/base")
public class UserController {
	
	@Autowired
	UserServiceImpl usrr;
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public User createUser(@Valid @RequestBody User user) {
		return usrr.addUser(user);
	}
	
	@GetMapping("/get/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public User getUser(@PathVariable Long id) {
		return usrr.getUser(id);
	}
	
	@GetMapping("/getAll")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public List<User> getAllUser() {
		return usrr.getAllUser(); 
	}

	
	@PutMapping("/update/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public User updateUser(@PathVariable Long id,@RequestBody User user) {
		return usrr.updateUser(id, user);
	}
	
	@DeleteMapping("delete/{id}")
	@PreAuthorize("hasRole('USER')")
	public String deleteUser(@PathVariable Long id) {
		
		return usrr.deleteUser(id);
	}

}

