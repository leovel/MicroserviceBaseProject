package com.leovel.gateway.security.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leovel.gateway.security.proxies.UserServiceProxy;
import com.leovel.gateway.security.services.*;
import com.leovel.gateway.security.jwt.*;
import com.leovel.gateway.security.models.CreateUserDTO;
import com.leovel.gateway.security.models.ERole;
import com.leovel.gateway.security.models.RoleDTO;
import com.leovel.gateway.security.payload.request.*;
import com.leovel.gateway.security.payload.response.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserServiceProxy userServiceProxy;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getUsername(),
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userServiceProxy.existsUserByUsername(signUpRequest.username())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userServiceProxy.existsUserByEmail(signUpRequest.email())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		var createUserDTO = new CreateUserDTO(encoder.encode(signUpRequest.password()));
		createUserDTO.setFullname(signUpRequest.fullname());
		createUserDTO.setEmail(signUpRequest.email());
		createUserDTO.setUsername(signUpRequest.username());

		Set<String> strRoles = signUpRequest.roles();
		Set<RoleDTO> roles = new HashSet<>();

		if (strRoles == null) {
			RoleDTO userRole = userServiceProxy.findRoleByName(ERole.ROLE_USER.name())
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					RoleDTO adminRole = userServiceProxy.findRoleByName(ERole.ROLE_ADMIN.name())
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				default:
					RoleDTO userRole = userServiceProxy.findRoleByName(ERole.ROLE_USER.name())
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		createUserDTO.setRoles(roles);
		userServiceProxy.createUser(createUserDTO);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}