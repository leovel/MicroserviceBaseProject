package com.leovel.gateway.security.proxies;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.leovel.gateway.security.models.BasicUserDTO;
import com.leovel.gateway.security.models.CreateUserDTO;
import com.leovel.gateway.security.models.RoleDTO;
import com.leovel.gateway.security.models.UpdateUserDTO;
import com.leovel.gateway.security.models.UserDTO;

@FeignClient(name = "user-service")
public interface UserServiceProxy {
	@PostMapping
	public Optional<BasicUserDTO> createUser(@RequestBody CreateUserDTO createUserDTO);
	
	@PutMapping
	public Optional<BasicUserDTO> updateUser(@RequestBody UpdateUserDTO updateUserDTO);
	
	@GetMapping("/{username}")
	public Optional<UserDTO> findUserByUserName(@PathVariable String username);
	
	@GetMapping("/exists/username/{username}")
	public Boolean existsUserByUsername(@PathVariable String username);
	
	@GetMapping("/exists/email/{email}")
	public Boolean existsUserByEmail(@PathVariable String email);
	
	@GetMapping("/role/{name}")
	public Optional<RoleDTO> findRoleByName(@PathVariable String name);
}
