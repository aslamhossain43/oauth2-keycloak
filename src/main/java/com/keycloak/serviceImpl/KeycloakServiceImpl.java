package com.keycloak.serviceImpl;

import java.util.Set;
import java.util.stream.Collectors;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keycloak.service.KeycloakService;
import com.keycloak.util.keycloak.KeycloakAdapter;

/**
 * Service Implementation for managing {@link QcAgent}.
 */
@Service
public class KeycloakServiceImpl implements KeycloakService {

	private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakServiceImpl.class);

	@Autowired
	KeycloakAdapter keycloakAdapter;

	public KeycloakServiceImpl() {
	}

	@Override
	public Set<UserRepresentation> getAllEnabledUsers() {
		LOGGER.info("Enter getAllEnabledUsers() in KeycloakUserServiceImpl class");
		Keycloak keycloak = keycloakAdapter.createKeycloakBuilder();
		keycloak.tokenManager().getAccessToken();
		Set<UserRepresentation> userRepresentations = keycloak.realm("trakti").roles().get("user").getRoleUserMembers()
				.stream().filter(u -> u.isEnabled()).collect(Collectors.toSet());
		return userRepresentations;
	}

	@Override
	public Set<UserRepresentation> getAllEnabledAdmins() {
		LOGGER.info("Enter getAllEnabledAdmins() in KeycloakUserServiceImpl class");
		Keycloak keycloak = keycloakAdapter.createKeycloakBuilder();
		keycloak.tokenManager().getAccessToken();
		Set<UserRepresentation> userRepresentations = keycloak.realm("trakti").roles().get("admin").getRoleUserMembers()
				.stream().filter(u -> u.isEnabled()).collect(Collectors.toSet());
		return userRepresentations;
	}

	@Override
	public UserRepresentation getKeycloakUserByUserId(String id) {
		LOGGER.info("Enter getKeycloakUserByUserId() in KeycloakUserServiceImpl class");
		Keycloak keycloak = keycloakAdapter.createKeycloakBuilder();
		keycloak.tokenManager().getAccessToken();
		UserRepresentation userRepresentation = keycloak.realm("trakti").users().get(id).toRepresentation();
		return userRepresentation;
	}
}
