package com.leovel.gateway.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leovel.gateway.security.proxies.UserServiceProxy;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserServiceProxy userServiceProxy;

	@Override
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    var user = userServiceProxy.findUserByUserName(username)
	        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

	    return UserDetailsImpl.build(user);
	  }

}
