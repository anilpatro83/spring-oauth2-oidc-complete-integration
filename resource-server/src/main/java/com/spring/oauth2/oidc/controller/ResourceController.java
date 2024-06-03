package com.spring.oauth2.oidc.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	@GetMapping("/user-resources")
	public List<String> getResources(){
		return Arrays.asList("resource1", "resource2");
	}
	
	@GetMapping("/resource1")
	public String resource1(){
		return "Resource 1";
	}

	@GetMapping("/resource2")
	public String resource2(){
		return "Resource 2";
	}

}
