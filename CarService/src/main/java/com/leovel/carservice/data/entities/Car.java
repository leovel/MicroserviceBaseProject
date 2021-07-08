package com.leovel.carservice.data.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"vin"})
@Entity
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private CarModel model;
	
	@Column(length = 16)
	private String vin;
	
	@Column(length = 64)
	private String color;
	
	private LocalDate dateOfRegitration;
	
	private Long ownerId;
}
