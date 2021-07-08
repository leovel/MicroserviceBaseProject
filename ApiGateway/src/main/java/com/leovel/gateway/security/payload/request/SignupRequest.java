package com.leovel.gateway.security.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record SignupRequest(
		@NotBlank
	    @Size(min = 3, max = 30)
		String fullname,
		@NotBlank
	    @Size(min = 3, max = 20)
		String username,
		@NotBlank
	    @Size(max = 50)
	    @Email
		String email,
		@NotBlank
	    @Size(min = 6, max = 40)
		String password,
		Set<String> roles) { }
