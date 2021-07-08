package com.leovel.carservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leovel.carservice.models.CreateCarDTO;
import com.leovel.carservice.services.CarService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CarController {
	
	@Autowired
	CarService carService;
	
	@PostMapping
	public ResponseEntity<?> createCar(@RequestBody CreateCarDTO createCarDTO) {
		var carDTO = carService.createCar(createCarDTO);
		
		return ResponseEntity.ok(carDTO);
	}
	
	@GetMapping("/{carId}")
	public ResponseEntity<?> findCarById(@PathVariable Long carId) {
		var fullCarDTO = carService.findCarById(carId);
		
		return fullCarDTO == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(fullCarDTO);
	}
}
