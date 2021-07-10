package com.leovel.gateway.userservice.components;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leovel.gateway.common.models.BasicUserDTO;
import com.leovel.gateway.common.models.UserDTO;
import com.leovel.gateway.userservice.data.entities.User;

@Component
public class MappingComponent {
	
	@Autowired
	ModelMapper modelMapper;

	public UserDTO mapUserToDTO(User user) {
		return user == null ? null : modelMapper.map(user, UserDTO.class);
	}
	
	public BasicUserDTO mapUserToBasicDTO(User user) {
		return user == null ? null : modelMapper.map(user, BasicUserDTO.class);
	}
	
	public <T extends BasicUserDTO> User mapToUser(T sourceDTO) {
		return sourceDTO == null ? null : modelMapper.map(sourceDTO, User.class);
	}
	
	public <T extends BasicUserDTO> void mapToUser(T sourceDTO, User destination) {
		if (sourceDTO != null && destination != null)
			modelMapper.map(sourceDTO, destination);
	}
}
