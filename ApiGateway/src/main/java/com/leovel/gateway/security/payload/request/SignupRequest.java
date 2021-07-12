package com.leovel.gateway.security.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
	@NotBlank
    @Size(min = 3, max = 30)
	private String fullname;
	@NotBlank
    @Size(min = 3, max = 20)
	private String username;
	@NotBlank
    @Size(max = 50)
    @Email
    private String email;
	@NotBlank
    @Size(min = 6, max = 40)
	private String password;
	private String role;
}
