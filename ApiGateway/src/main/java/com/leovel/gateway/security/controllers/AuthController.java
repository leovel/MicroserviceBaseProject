package com.leovel.gateway.security.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leovel.gateway.security.configuration.AuthenticationManager;
import com.leovel.gateway.security.jwt.*;
import com.leovel.gateway.security.payload.request.*;
import com.leovel.gateway.security.payload.response.*;
import com.leovel.gateway.security.services.PBKDF2Encoder;
import com.leovel.gateway.security.userservice.proxy.UserServiceProxy;
import com.leovel.userservice.common.Roles;
import com.leovel.userservice.models.CreateUserDTO;

import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserServiceProxy userServiceProxy;

	@Autowired
	PBKDF2Encoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public Mono<ResponseEntity<JwtResponse>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return userServiceProxy.findUserByUserName(loginRequest.getUsername())
	            .filter(userDetails -> encoder.encode(loginRequest.getPassword()).equals(userDetails.getPassword()))
	            .map(userDetails -> ResponseEntity.ok(new JwtResponse(
	            		jwtUtils.generateJwtToken(userDetails),
	            		userDetails.getFullname())))
	            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
	}

	@PostMapping("/signup")
	public Mono<ResponseEntity<MessageResponse>> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return userServiceProxy.existsUserByUsername(signUpRequest.getUsername())
		.filter(existByUsername -> existByUsername)
		.map(existByUsername ->  ResponseEntity.badRequest()
				.body(new MessageResponse("Error: Username is already taken!")))
		.switchIfEmpty(
				userServiceProxy.existsUserByEmail(signUpRequest.getEmail())
				.filter(existByEmail -> existByEmail)
				.map(existByEmail ->  ResponseEntity.badRequest()
						.body(new MessageResponse("Error: Email is already in use!"))))
		.switchIfEmpty(tokenResponse(signUpRequest));
	}
	
	private Mono<ResponseEntity<MessageResponse>> tokenResponse(SignupRequest signUpRequest) {
		var createUserDTO = new CreateUserDTO(encoder.encode(signUpRequest.getPassword()));
		createUserDTO.setFullname(signUpRequest.getFullname());
		createUserDTO.setEmail(signUpRequest.getEmail());
		createUserDTO.setUsername(signUpRequest.getUsername());
		createUserDTO.setRole(StringUtils.hasText(signUpRequest.getRole()) ?
				signUpRequest.getRole() :
				Roles.ROLE_USER.name());

		return userServiceProxy.createUser(createUserDTO).map(user ->
		ResponseEntity.ok(new MessageResponse("User " + user.getUsername() + " registered successfully!")));
	
	}
}