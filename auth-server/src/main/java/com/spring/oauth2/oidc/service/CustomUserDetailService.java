package com.spring.oauth2.oidc.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.oauth2.oidc.dto.repo.UserRepo;

public class CustomUserDetailService implements UserDetailsService {

	private UserRepo repo;
	private PasswordEncoder encoder;
	
	public CustomUserDetailService(UserRepo repo, PasswordEncoder encoder) {
		this.repo = repo;
		this.encoder = encoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Object[]> userDetails = repo.findByUserEmail(username);
		if (userDetails == null || userDetails.isEmpty()) {
			throw new UsernameNotFoundException("User account with " + username + " not found");
		}
		return User.builder().username((String) userDetails.get(0)[0]).password((String) userDetails.get(0)[1])
				.passwordEncoder(encoder::encode).authorities(getAuthorities(userDetails)).build();
	}

	private Collection<? extends GrantedAuthority> getAuthorities(List<Object[]> userDetails) {
		return userDetails.stream().map(mapper -> new SimpleGrantedAuthority((String) mapper[2]))
				.collect(Collectors.toSet());
	}

}
