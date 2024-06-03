package com.spring.oauth2.oidc.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Securityonfig {

	@Bean
	SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/**")
				.authorizeHttpRequests(request -> 
						request.requestMatchers("/resource1").hasAnyAuthority("read_all","read_resource_1").
						requestMatchers("/resource2").hasAnyAuthority("read_all","read_resource_2").
						anyRequest().hasAuthority("SCOPE_oidc-client.read"))
				.oauth2ResourceServer(
						oauth2 -> oauth2.jwt(
								jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));
		return http.build();
	}

	private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
		converter.setJwtGrantedAuthoritiesConverter(grantedAuthorityConverter());
		return converter;
	}

	@SuppressWarnings("unchecked")
	private Converter<Jwt, Collection<GrantedAuthority>> grantedAuthorityConverter() {
		return ((source) -> {
			Collection<GrantedAuthority> authorities = ((List<String>) source.getClaim("scope")).stream()
					.map(mapper -> new SimpleGrantedAuthority("SCOPE_" + mapper)).collect(Collectors.toSet());
			authorities.addAll(Stream.of(((String) source.getClaim("roles")).split(" "))
					.map(mapper -> new SimpleGrantedAuthority(mapper)).collect(Collectors.toSet()));
			return authorities;
		});
	}
}
