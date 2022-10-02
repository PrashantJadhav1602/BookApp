package com.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// find user from database where user = :username
// userRepo.findByUsername(username);// username, password, roles

		if ("sumit".equals(username)) {
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
	        authorities.add(new SimpleGrantedAuthority("ROLE_" + "DIRECTOR"));
//	        authorities.add(new SimpleGrantedAuthority("ROLE_" + "OWNER"));
			
			return new User(
					"sumit", 
//					"{noop}demo@123", 
					"$2a$10$ImVA6b1HFYDKA0766fhmTe7CKdyPVfgXe77.rRjRFuzx/6f4v/TDO",
					authorities
				);
		} else if ("syed".equals(username)) {
				Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		        authorities.add(new SimpleGrantedAuthority("ROLE_" + "ADMIN"));
//		        authorities.add(new SimpleGrantedAuthority("ROLE_" + "OWNER"));
				
				return new User(
						"syed", 
//						"{noop}demo@123", 
						"$2a$10$ImVA6b1HFYDKA0766fhmTe7CKdyPVfgXe77.rRjRFuzx/6f4v/TDO",
						authorities
					);
			} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
//
	}
}