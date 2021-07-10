package com.leovel.userservice.data.entities;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"username"})
@Entity
@Table( name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email")
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 128)
	private String fullname;
	
	@Column(length = 32)
	private String username;
	
	@Column(length = 64)
	private String email;
	
	@Column(length = 1024)
	private String password;
	
	@Column(length = 64)
	private String role;
}
