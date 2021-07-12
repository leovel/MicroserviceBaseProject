package com.leovel.userservice.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"username"})
public class BasicUserDTO {
	@NotBlank
	private String fullname;
	@NotBlank
	private String username;
	@NotBlank
	@Email
	private String email;
	private String role;
}
