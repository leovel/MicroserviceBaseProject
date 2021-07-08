package com.leovel.officerservice.models;

import javax.validation.constraints.NotBlank;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"identificationNumber"})
public class CreateOfficerDTO {
	@NotBlank
	private String identificationNumber;
	private String fullName;
	private String department;
	private String position;
}
