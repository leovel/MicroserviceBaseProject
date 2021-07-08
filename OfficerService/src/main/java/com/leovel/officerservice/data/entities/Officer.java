package com.leovel.officerservice.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"identificationNumber"})
@Entity
public class Officer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 16)
	private String identificationNumber;
	
	@Column(length = 128)
	private String fullName;
	
	@Column(length = 128)
	private String department;
	
	@Column(length = 64)
	private String position;
}
