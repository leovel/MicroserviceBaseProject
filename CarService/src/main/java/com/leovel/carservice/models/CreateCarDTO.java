package com.leovel.carservice.models;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"vin"})
public class CreateCarDTO {
	private CarModelDTO model;
	@NotBlank
	private String vin;
	private String color;	
	private LocalDate dateOfRegitration;
	private Long ownerId;
}
