package com.keycloak.service;

import java.util.Set;

import org.keycloak.representations.idm.UserRepresentation;

/**
 * Service Interface for managing {@link QcAgent}.
 */
public interface KeycloakService {
	Set<UserRepresentation> getAllEnabledUsers();

	UserRepresentation getKeycloakUserByUserId(String id);

	Set<UserRepresentation> getAllEnabledAdmins();
}
