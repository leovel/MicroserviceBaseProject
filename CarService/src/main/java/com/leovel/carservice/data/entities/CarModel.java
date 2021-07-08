package com.leovel.carservice.data.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.leovel.carservice.common.CarType;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name", "year", "brand"})
@Embeddable
public class CarModel {
	
	@Column(length = 64)
	private String name;
	
	private Integer year;
	
	@Column(length = 64)
	private String brand;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 64)
	private CarType carType;
}
