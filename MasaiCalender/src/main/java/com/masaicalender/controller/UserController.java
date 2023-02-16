package com.masaicalender.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masaicalender.model.LoginDTO;
import com.masaicalender.model.User;
import com.masaicalender.service.UserService;

@RestController
@RequestMapping("/masaicalender")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user){
		
		User registerUser = userService.registerUser(user);
		
		return new ResponseEntity<User>(registerUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginDTO dto){
		
		String res = userService.loginUser(dto);
		
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}
	
	@PutMapping("/user/{uid}")
	public ResponseEntity<User> updateCredentials(@RequestBody User user, @PathVariable("uid") String uniqueId){
		
		User updateUser = userService.updateCredentials(user, uniqueId);
		
		return new ResponseEntity<User>(updateUser,HttpStatus.OK);
	}
}
