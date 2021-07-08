package com.leovel.carservice.components;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leovel.carservice.data.entities.Car;
import com.leovel.carservice.models.CarDTO;
import com.leovel.carservice.models.CreateCarDTO;
import com.leovel.carservice.models.FullCarDTO;
import com.leovel.carservice.models.OfficerDTO;
import com.leovel.carservice.proxies.OfficerServiceProxy;

@Component
public class MappingComponent {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	OfficerServiceProxy officerServiceProxy;

	public CarDTO mapCarToDTO(Car car) {
		return car == null ? null : modelMapper.map(car, CarDTO.class);
	}
	
	public FullCarDTO mapCarToFullDTO(Car car) {
		var fullCar = car == null ? null : modelMapper.map(car, FullCarDTO.class);
		
		if(fullCar != null) {
			fullCar.setOwner(modelMapper.map(officerServiceProxy.findOfficerById(fullCar.getOwnerId()), OfficerDTO.class));
		}
		
		return fullCar;
	}
	
	public Car mapToCar(CarDTO carDTO) {
		return carDTO == null ? null : modelMapper.map(carDTO, Car.class);
	}
	
	public Car mapToCar(CreateCarDTO createCarDTO) {
		return createCarDTO == null ? null : modelMapper.map(createCarDTO, Car.class);
	}
}
