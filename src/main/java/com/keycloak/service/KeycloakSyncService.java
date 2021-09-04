package com.keycloak.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.keycloak.model.KeycloakUser;

/**
 * Service Interface for managing {@link KeycloakUser}.
 */
public interface KeycloakSyncService {

	/**
	 * Save a keycloakSyncUser.
	 *
	 * @param keycloakUser the entity to save.
	 * @return the persisted entity.
	 */
	KeycloakUser save(KeycloakUser keycloakUser);

	/**
	 * Get all the keycloakSyncUsers.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	Page<KeycloakUser> findAll(Pageable pageable);

	/**
	 * Get the "id" keycloakSyncUser.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	Optional<KeycloakUser> findOne(String id);

	/**
	 * Delete the "id" keycloakSyncUser.
	 *
	 * @param id the id of the entity.
	 */
	void delete(String id);

	void syncKeycloak();
}
