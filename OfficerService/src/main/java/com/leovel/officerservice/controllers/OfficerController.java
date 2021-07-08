package com.leovel.officerservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leovel.officerservice.models.CreateOfficerDTO;
import com.leovel.officerservice.services.OfficerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OfficerController {
	
	@Autowired
	OfficerService officerService;
	
	@PostMapping
	public ResponseEntity<?> createOfficer(@RequestBody CreateOfficerDTO createOfficerDTO) {
		var officerDTO = officerService.createOfficer(createOfficerDTO);
		
		return ResponseEntity.ok(officerDTO);
	}
	
	@GetMapping("/{officerId}")
	public ResponseEntity<?> findOfficerById(@PathVariable Long officerId) {
		var officerDTO = officerService.findOfficerById(officerId);
		
		return officerDTO == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(officerDTO);
	}
}
