package com.leovel.gateway.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leovel.gateway.userservice.services.UserService;

import reactor.core.publisher.Mono;

@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {
	@Autowired
	UserService userService;
	
	@Override
	public Mono<UserDetails> findByUsername(String username) {
		return userService.findUserByName(username)
				.switchIfEmpty(Mono.error(new UsernameNotFoundException("User Not Found with username: " + username)))
				.flatMap(user -> Mono.justOrEmpty(UserDetailsImpl.build(user)));
	}

}
