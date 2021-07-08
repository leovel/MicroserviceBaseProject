package com.leovel.gateway.security.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class RoleDTO {
	private ERole name;
}
