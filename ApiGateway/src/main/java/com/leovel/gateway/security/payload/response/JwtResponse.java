package com.leovel.gateway.security.payload.response;

import java.util.List;

public record JwtResponse(
		String accessToken,
		String username,
		String email,
		List<String> roles) { }
