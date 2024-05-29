package com.spring.oauth2.oidc.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	@GetMapping("/user-resources")
	public List<String> getResources(){
		return Arrays.asList("Resource 1", "Resource 2", "Resource 3");
	}
}
