package com.leovel.carservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leovel.carservice.components.MappingComponent;
import com.leovel.carservice.data.repositories.CarRepository;
import com.leovel.carservice.models.*;

@Service
public class CarService {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	MappingComponent mappingComponent;
	
	public CarDTO createCar(CreateCarDTO createCarDTO) {
		
		var car = mappingComponent.mapToCar(createCarDTO);
		
		return mappingComponent.mapCarToDTO(carRepository.save(car));
	}
	
	public FullCarDTO findCarById(Long id) {
		return mappingComponent.mapCarToFullDTO(carRepository.findById(id).orElse(null));
	}
}
