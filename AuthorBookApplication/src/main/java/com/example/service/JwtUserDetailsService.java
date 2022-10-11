package com.example.service;

import java.util.HashSet;
import java.util.Set;

import org.bouncycastle.openssl.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Author;
import com.example.models.JwtRequest;
import com.example.repository.AuthorRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	AuthorRepository authorRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// find user from database where user = :username
// userRepo.findByUsername(username);// username, password, roles

		if ("demo".equals(username)) {
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//	        authorities.add(new SimpleGrantedAuthority("ROLE_" + "DIRECTOR"));
//	        authorities.add(new SimpleGrantedAuthority("ROLE_" + "OWNER"));
			
			return new User(
					"demo", 
//					"{noop}demo@123", 
					"$2a$10$eGqiYP/UPpBNhknK4QX1gOhMVXY49Gf24T/3LnHIPCKawumlMJWRC",
					authorities
				);
		} else if ("syed".equals(username)) {
				Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//		        authorities.add(new SimpleGrantedAuthority("ROLE_" + "ADMIN"));
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
	
	public UserDetails loadUserByUsernameAndPassword(JwtRequest req){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedStr = encoder.encode(req.getPassword());
		System.out.println("before authorRepository.findByUsername =================");
		Author author = authorRepository.findByUsername(req.getUsername());
		System.out.println("after =======================");
		if (!author.equals(null)) {
			if(encoder.matches(req.getPassword(), author.getPassword())) {
				System.out.println("in password correct +++++++++++=");
				Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//	        authorities.add(new SimpleGrantedAuthority("ROLE_" + "DIRECTOR"));
//	        authorities.add(new SimpleGrantedAuthority("ROLE_" + "OWNER"));
				
				return new User(
						req.getUsername(), 
//					"{noop}demo@123", 
						req.getPassword(),
						authorities
						);
			}else {
				throw new UsernameNotFoundException("Password invalid: " + req.getUsername());
			}
		}
		else {
			throw new UsernameNotFoundException("User not found with username: " + req.getUsername());
		}
//
	}
}