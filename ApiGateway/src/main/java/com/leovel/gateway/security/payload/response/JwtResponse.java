package com.leovel.gateway.security.payload.response;

public record JwtResponse(
		String accessToken,
		String fullname) { }
