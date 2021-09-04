package com.keycloak.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.keycloak.model.KeycloakAdminUser;

/**
 *
 * @author Md. Aslam Hossain
 * @version 05/09/2021
 *
 */
public interface KeycloakAdminUserService {
	KeycloakAdminUser save(KeycloakAdminUser keycloakAdminUser);

	List<KeycloakAdminUser> getAll(Pageable pageable);

	KeycloakAdminUser getById(String id);

	void deleteById(String id);

	void deleteAll();

	void syncKeycloak();
}
