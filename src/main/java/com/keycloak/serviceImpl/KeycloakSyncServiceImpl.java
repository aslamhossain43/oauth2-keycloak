package com.keycloak.serviceImpl;

import java.util.Optional;
import java.util.Set;

import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.keycloak.model.KeycloakUser;
import com.keycloak.repository.KeycloakSyncRepository;
import com.keycloak.service.KeycloakService;
import com.keycloak.service.KeycloakSyncService;

/**
 * Service Implementation for managing {@link KeycloakUser}.
 */
@Service
public class KeycloakSyncServiceImpl implements KeycloakSyncService {

	private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakSyncServiceImpl.class);
	@Autowired
	private KeycloakSyncRepository keycloakSyncRepository;
	@Autowired
	private KeycloakService keycloakService;

	@Override
	public KeycloakUser save(KeycloakUser keycloakUser) {
		LOGGER.info("Enter save() in KeycloakSyncServiceImpl class");
		return keycloakSyncRepository.save(keycloakUser);
	}

	@Override
	public Page<KeycloakUser> findAll(Pageable pageable) {
		LOGGER.info("Enter findAll() in KeycloakSyncServiceImpl class");
		return keycloakSyncRepository.findAll(pageable);
	}

	@Override
	public Optional<KeycloakUser> findOne(String id) {
		LOGGER.info("Enter findOne() in KeycloakSyncServiceImpl class");
		return keycloakSyncRepository.findById(id);
	}

	@Override
	public void delete(String id) {
		LOGGER.info("Enter delete() in KeycloakSyncServiceImpl class");
		keycloakSyncRepository.deleteById(id);
	}

	@Override
	public void syncKeycloak() {
		LOGGER.info("Enter syncKeycloak() in KeycloakSyncServiceImpl class");
		Set<UserRepresentation> qcAgents = this.keycloakService.getAllEnabledUsers();
		saveKeycloakSyncUser(qcAgents);
		Set<UserRepresentation> qcAdmins = this.keycloakService.getAllEnabledAdmins();
		saveKeycloakSyncUser(qcAdmins);
	}

	private void saveKeycloakSyncUser(Set<UserRepresentation> userRepresentations) {
		for (UserRepresentation userRepresentation : userRepresentations) {
			KeycloakUser keycloakUser = new KeycloakUser();
			keycloakUser.setId(userRepresentation.getId());
			keycloakUser.setEmail(userRepresentation.getEmail());
			keycloakUser.setFirstName(userRepresentation.getFirstName());
			keycloakUser.setLastName(userRepresentation.getLastName());
			this.keycloakSyncRepository.save(keycloakUser);
		}
	}

}
