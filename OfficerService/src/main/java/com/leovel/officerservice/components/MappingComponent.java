package com.leovel.officerservice.components;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leovel.officerservice.data.entities.Officer;
import com.leovel.officerservice.models.CreateOfficerDTO;
import com.leovel.officerservice.models.OfficerDTO;


@Component
public class MappingComponent {
	
	@Autowired
	ModelMapper modelMapper;

	public OfficerDTO mapOfficerToDTO(Officer officer) {
		return officer == null ? null : modelMapper.map(officer, OfficerDTO.class);
	}
	
	public Officer mapToOfficer(OfficerDTO officerDTO) {
		return officerDTO == null ? null : modelMapper.map(officerDTO, Officer.class);
	}
	
	public Officer mapToOfficer(CreateOfficerDTO createOfficerDTO) {
		return createOfficerDTO == null ? null : modelMapper.map(createOfficerDTO, Officer.class);
	}
}
