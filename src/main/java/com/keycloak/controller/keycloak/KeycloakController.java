package com.keycloak.controller.keycloak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keycloak.service.KeycloakAdminUserService;

/**
 *
 * @author Md. Aslam Hossain
 * @version 05/09/2021
 *
 */
@RestController
@RequestMapping("/api/keycloak/sync")
public class KeycloakController {
	private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakController.class);
	@Autowired
	private KeycloakAdminUserService keycloakAdminUserService;

	@GetMapping
	public ResponseEntity<String> syncKeycloak() {
		LOGGER.info("Enter syncKeycloak() in KeycloakController class");
		keycloakAdminUserService.syncKeycloak();
		return ResponseEntity.ok().body("Keycloak is synced successfully");
	}
}
