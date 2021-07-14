package com.leovel.userservice.models;

import javax.validation.constraints.NotBlank;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true, exclude = {"password"})
public class CreateUserDTO extends BasicUserDTO {
	@NotBlank
	private String password;
}
