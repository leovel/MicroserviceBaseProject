package com.leovel.officerservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leovel.officerservice.components.MappingComponent;
import com.leovel.officerservice.data.repositories.OfficerRepository;
import com.leovel.officerservice.models.*;

@Service
public class OfficerService {
	@Autowired
	OfficerRepository officerRepository;
	
	@Autowired
	MappingComponent mappingComponent;
	
	public OfficerDTO createOfficer(CreateOfficerDTO createOfficerDTO) {
		
		var officer = mappingComponent.mapToOfficer(createOfficerDTO);
		
		return mappingComponent.mapOfficerToDTO(officerRepository.save(officer));
	}
	
	public OfficerDTO findOfficerById(Long id) {
		return mappingComponent.mapOfficerToDTO(officerRepository.findById(id).orElse(null));
	}

}
