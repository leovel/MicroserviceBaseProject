package com.leovel.carservice.models;

import com.leovel.carservice.common.CarType;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name", "year", "brand"})
public class CarModelDTO {
	private String name;	
	private Integer year;
	private String brand;
	private CarType carType;
}
