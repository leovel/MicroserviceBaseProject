package com.leovel.userservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leovel.userservice.common.ERole;
import com.leovel.userservice.components.MappingComponent;
import com.leovel.userservice.data.entities.User;
import com.leovel.userservice.data.repositories.RoleRepository;
import com.leovel.userservice.data.repositories.UserRepository;
import com.leovel.userservice.models.*;


@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	MappingComponent mappingComponent;
	
	public Optional<BasicUserDTO> createUser(CreateUserDTO createUserDTO) {
		
		var user = mappingComponent.mapToUser(createUserDTO);
		
		if(user != null) {
			return Optional.of(mappingComponent
					.mapUserToBasicDTO(userRepository
							.save(user)));
		}
		
		return Optional.empty();
	}
	
	public Optional<BasicUserDTO> updateUser(UpdateUserDTO updateUserDTO) {
		
		if(updateUserDTO != null) {
			
			var user = findDbUserByName(updateUserDTO.getUsername());
			
			if(user != null) {
				
				mappingComponent.mapToUser(updateUserDTO, user);
				
				return Optional.of(mappingComponent
						.mapUserToBasicDTO(userRepository
								.save(user)));
			}
			
		}
		
		return Optional.empty();
	}
	
	private User findDbUserByName(String username) {
		return userRepository
				.findByUsername(username)
				.orElse(null);
	}
		
	public Optional<UserDTO> findUserByName(String userName) {
		return Optional.ofNullable(mappingComponent
				.mapUserToDTO(findDbUserByName(userName)));
	}
	
	public Boolean existsUserByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	
	public Boolean existsUserByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	public Optional<RoleDTO> findRoleByName(String name) {
		return Optional.ofNullable(mappingComponent
				.mapRoleToDTO(roleRepository
						.findByName(ERole.valueOf(name))
						.orElse(null)));
	}
}
