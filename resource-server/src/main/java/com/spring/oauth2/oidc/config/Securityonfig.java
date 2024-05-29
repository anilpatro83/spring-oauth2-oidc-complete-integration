package com.spring.oauth2.oidc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Securityonfig {

	@Bean
	SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/**")
				.authorizeHttpRequests(request -> 
					request.anyRequest().hasAuthority("SCOPE_oidc-client.read"))
				.oauth2ResourceServer(oauth2 -> 
					oauth2.jwt(Customizer.withDefaults()));
		return http.build();
	}
}
