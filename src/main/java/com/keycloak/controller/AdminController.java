package com.keycloak.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdminController {
	@GetMapping(value = "/admin/hello")
	public String admin() {
		return "Admin hello";
	}
}
