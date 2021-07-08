package com.leovel.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leovel.userservice.models.CreateUserDTO;
import com.leovel.userservice.models.UpdateUserDTO;
import com.leovel.userservice.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody CreateUserDTO createUserDTO) {
		return ResponseEntity.ok(userService.createUser(createUserDTO));
	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
		return ResponseEntity.ok(userService.updateUser(updateUserDTO));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?> findUserByUserName(@PathVariable String username) {
		return ResponseEntity.ok(userService.findUserByName(username));
	}
	
	@GetMapping("/exists/username/{username}")
	public ResponseEntity<?> existsUserByUsername(@PathVariable String username) {
		return ResponseEntity.ok(userService.existsUserByUsername(username));
	}
	
	@GetMapping("/exists/email/{email}")
	public ResponseEntity<?> existsUserByEmail(@PathVariable String email) {
		return ResponseEntity.ok(userService.existsUserByEmail(email));
	}
	
	@GetMapping("/role/{name}")
	public ResponseEntity<?> findRoleByName(@PathVariable String name) {
		return ResponseEntity.ok(userService.findRoleByName(name));
	}

}
