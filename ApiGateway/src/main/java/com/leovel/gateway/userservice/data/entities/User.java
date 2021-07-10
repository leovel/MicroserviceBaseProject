package com.leovel.gateway.userservice.data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"username"})
@Table("users")
public class User {
	@Id
	private Long id;
	
	private String fullname;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String role;
}
