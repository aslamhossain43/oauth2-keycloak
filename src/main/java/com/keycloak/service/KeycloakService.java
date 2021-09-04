package com.keycloak.service;

import java.util.Set;

import org.keycloak.representations.idm.UserRepresentation;

/**
 *
 * @author Md. Aslam Hossain
 * @version 05/09/2021
 *
 */
public interface KeycloakService {
	Set<UserRepresentation> getAllEnabledUsers();

	Set<UserRepresentation> getAllEnabledAdmins();

	UserRepresentation getKeycloakAdminUserId(String id);

}
