package com.spring.oauth2.oidc.controller;

import java.util.ArrayList;
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
		builder.append(getResources(authorizedClient.getAccessToken().getTokenValue()));
		return builder.toString();
	}

	@SuppressWarnings("unchecked")
	private List<String> getResources(String token) {
		String response = null;
		List<String> userResources = new ArrayList<>();
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(token);
		HttpEntity<Void> req = new HttpEntity<>(headers);
		List<String> resources = (List<String>) callResources(url + "/user-resources", req, List.class);
		for (String resource : resources) {
			response = (String) callResources(url + "/" + resource, req, String.class);
			if (response != null) {
				userResources.add(response);
			}
		}
		return userResources;
	}

	@SuppressWarnings({ "rawtypes" })
	private Object callResources(String url, HttpEntity req, Class<?> type) {
		Object response = null;
		try {
			response = restClient.exchange(url, HttpMethod.GET, req, type).getBody();
		} catch (Exception e) {
		}
		return response;
	}

}
