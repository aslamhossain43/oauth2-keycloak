package com.keycloak.controller.keycloak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keycloak.service.KeycloakSyncService;

/**
 * REST controller for managing {@link VerifiableField}.
 */
@RestController
@RequestMapping("/api/keycloak/sync")
public class KeycloakSyncController {

	@Autowired
	private KeycloakSyncService keycloakSyncService;

	@GetMapping
	public void sync() {
		keycloakSyncService.syncKeycloak();
	}
}
