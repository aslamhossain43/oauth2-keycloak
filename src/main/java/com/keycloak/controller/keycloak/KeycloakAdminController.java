package com.keycloak.controller.keycloak;

import java.util.Set;

import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keycloak.service.KeycloakService;

@RestController
@RequestMapping("/api/keycloak/admin")
public class KeycloakAdminController {
	private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakAdminController.class);
	@Autowired
	private KeycloakService keycloakService;

	@GetMapping
	public Set<UserRepresentation> getAll() {
		LOGGER.info("Enter getAll() in KeycloakAdminController class");
		return keycloakService.getAllEnabledAdmins();
	}
}
