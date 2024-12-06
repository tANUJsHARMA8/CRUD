package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.UserRepo;

@Service
public class UserServiceImpl {
	
	@Autowired
	UserRepo userrepo;
	
	public User addUser(User user) {
		
	//	user.setId(UUID.randomUUID().toString());
	
	return	userrepo.save(user);
		
	}
	
	public User getUser(Long id) {
		
		
		User user= userrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User does not exist on this id===== "+id));
		
		return user;
	}
	
	//list of user
	
	public List<User> getAllUser(){
		return userrepo.findAll();
	}
	
	
	public User updateUser(Long id, User user) {
		
		 User user2 = userrepo.findById(id).get();
		 user2.setId(user.getId());
		 user2.setName(user.getName());
		 user2.setAddress(user.getAddress());
		 
		 User save = userrepo.save(user2);
		 
		 return save;
	}
	
	public String deleteUser(Long id) {
		 userrepo.deleteById(id);
		 return "Deleted";
	}

}
