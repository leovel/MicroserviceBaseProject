package com.leovel.userservice.models;

import com.leovel.userservice.common.ERole;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class RoleDTO {
	private ERole name;
}
