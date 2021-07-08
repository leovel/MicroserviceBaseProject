package com.leovel.carservice.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true, exclude = {"id"})
public class CarDTO extends CreateCarDTO {
	private Long id;
}
