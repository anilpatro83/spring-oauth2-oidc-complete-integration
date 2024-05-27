package com.spring.oauth2.oidc.controller;

import java.security.Principal;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


	@GetMapping("/")
	public String user(Principal principal) {
		StringBuilder builder = new StringBuilder();
		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
		OAuth2User user = token.getPrincipal();
		builder.append("Welcome " + user.getName() + " ! </br>");
		builder.append("OIDC Client registered with :  " + token.getAuthorizedClientRegistrationId());
		return builder.toString();
	}

}
