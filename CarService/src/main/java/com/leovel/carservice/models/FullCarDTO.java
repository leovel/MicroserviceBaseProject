package com.leovel.carservice.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true, exclude = {"owner"})
public class FullCarDTO extends CarDTO {
	private OfficerDTO owner;
}
