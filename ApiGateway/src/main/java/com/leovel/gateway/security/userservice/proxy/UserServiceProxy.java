package com.leovel.gateway.security.userservice.proxy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.leovel.userservice.models.*;

import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "user-service")
public interface UserServiceProxy {
	
	@PostMapping
	public Mono<BasicUserDTO> createUser(@RequestBody CreateUserDTO createUserDTO);
	
	@PutMapping
	public Mono<BasicUserDTO> updateUser(@RequestBody UpdateUserDTO updateUserDTO);
	
	@GetMapping("/{username}")
	public Mono<UserDTO> findUserByUserName(@PathVariable String username);
	
	@GetMapping("/exists/username/{username}")
	public Mono<Boolean> existsUserByUsername(@PathVariable String username);
	
	@GetMapping("/exists/email/{email}")
	public Mono<Boolean> existsUserByEmail(@PathVariable String email);

}
