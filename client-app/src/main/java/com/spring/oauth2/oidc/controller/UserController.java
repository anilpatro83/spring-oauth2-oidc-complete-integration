package com.spring.oauth2.oidc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

	
	 @Autowired
	 private RestTemplate restClient;
	
	 @Value("${resource.server.url}")
	 private String url;

	
	
	 @GetMapping("/")
	 public String userResources(
			@RegisteredOAuth2AuthorizedClient("oidc-client-authorization-code") OAuth2AuthorizedClient authorizedClient) {
	 	StringBuilder builder = new StringBuilder();
	 	builder.append("Welcome " + authorizedClient.getPrincipalName() + " ! </br>");
	 	builder.append("User resources :  ");
	 	HttpHeaders headers = new HttpHeaders();
	 	headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue());
	 	HttpEntity<Void> req = new HttpEntity<>(headers);
	 	builder.append(restClient.exchange(url+"/user-resources", HttpMethod.GET, req, List.class).getBody());
	  	return builder.toString();
	 }
	
}
