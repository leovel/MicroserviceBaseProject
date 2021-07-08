package com.leovel.officerservice.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true, exclude = {"id"})
public class OfficerDTO extends CreateOfficerDTO {
	private Long id;
}
