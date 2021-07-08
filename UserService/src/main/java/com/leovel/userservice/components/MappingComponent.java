package com.leovel.userservice.components;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leovel.userservice.data.entities.Role;
import com.leovel.userservice.data.entities.User;
import com.leovel.userservice.models.BasicUserDTO;
import com.leovel.userservice.models.RoleDTO;
import com.leovel.userservice.models.UserDTO;

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
	
	public RoleDTO mapRoleToDTO(Role role) {
		return role == null ? null : modelMapper.map(role, RoleDTO.class);
	}
	
	public Role mapToRole(RoleDTO sourceDTO) {
		return sourceDTO == null ? null : modelMapper.map(sourceDTO, Role.class);
	}
}
