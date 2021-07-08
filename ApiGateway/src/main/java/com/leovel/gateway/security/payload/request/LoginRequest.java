package com.leovel.gateway.security.payload.request;

import javax.validation.constraints.NotBlank;

public record LoginRequest(
		@NotBlank String username,
		@NotBlank String password) { }
