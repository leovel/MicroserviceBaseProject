package com.leovel.gateway.security.models;

import java.util.HashSet;
import java.util.Set;

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
	private Set<RoleDTO> roles = new HashSet<>();
}
