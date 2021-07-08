package com.leovel.carservice.models;

import javax.validation.constraints.NotBlank;

import lombok.*;

@Data
public class OfficerDTO {
	@NotBlank
	private Long id;
	@NotBlank
	private String identificationNumber;
	private String fullName;
	private String department;
	private String position;
}
