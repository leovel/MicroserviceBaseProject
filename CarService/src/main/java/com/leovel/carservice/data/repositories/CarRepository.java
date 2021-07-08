package com.leovel.carservice.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leovel.carservice.data.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
	List<Car> findByOwnerId(Long ownerId);
}
