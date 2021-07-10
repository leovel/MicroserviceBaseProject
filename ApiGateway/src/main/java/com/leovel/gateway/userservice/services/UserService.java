package com.leovel.gateway.userservice.services;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leovel.gateway.common.models.BasicUserDTO;
import com.leovel.gateway.common.models.CreateUserDTO;
import com.leovel.gateway.common.models.UpdateUserDTO;
import com.leovel.gateway.common.models.UserDTO;
import com.leovel.gateway.userservice.components.MappingComponent;
import com.leovel.gateway.userservice.data.repositories.UserRepository;


@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	MappingComponent mappingComponent;
	
	public Mono<BasicUserDTO> createUser(CreateUserDTO createUserDTO) {
		
		var user = mappingComponent.mapToUser(createUserDTO);
		
		
		if(user != null) {
			return userRepository
					.save(user)
					.map(u -> mappingComponent.mapUserToBasicDTO(u));
		} else {
			return Mono.empty();
		}
	}
	
	public Mono<BasicUserDTO> updateUser(UpdateUserDTO updateUserDTO) {
		
		if(updateUserDTO != null) {
			
			
			return userRepository.findByUsername(updateUserDTO.getUsername())
					.flatMap(user -> {
						mappingComponent.mapToUser(updateUserDTO, user);
						return user == null ?  Mono.empty() : userRepository
								.save(user)
								.map(u -> mappingComponent.mapUserToBasicDTO(u));
					});
			
		} else {
			return Mono.empty();
		}
	}
	
		
	public Mono<UserDTO> findUserByName(String userName) {
		return userRepository.findByUsername(userName)
				.map(user -> mappingComponent.mapUserToDTO(user));
	}
	
	public Mono<Boolean> existsUserByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	
	public Mono<Boolean> existsUserByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
}
