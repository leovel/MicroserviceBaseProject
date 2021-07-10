package com.leovel.gateway.userservice.data.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.leovel.gateway.userservice.data.entities.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<User, Long> {
	Mono<User> findByUsername(String username);
	Mono<Boolean> existsByUsername(String username);
	Mono<Boolean> existsByEmail(String email);
}
