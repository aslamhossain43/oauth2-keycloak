package com.keycloak.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.keycloak.model.KeycloakAdminUser;
import com.keycloak.repository.KeycloakAdminUserRepository;
import com.keycloak.service.KeycloakAdminUserService;
import com.keycloak.service.KeycloakService;

/**
 *
 * @author Md. Aslam Hossain
 * @version 05/09/2021
 *
 */
@Service
public class KeycloakAdminUserServiceImpl implements KeycloakAdminUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakAdminUserServiceImpl.class);
	@Autowired
	private KeycloakAdminUserRepository keycloakAdminUserRepository;
	@Autowired
	private KeycloakService keycloakService;

	@Override
	public KeycloakAdminUser save(KeycloakAdminUser keycloakAdminUser) {
		LOGGER.info("Enter save() in KeycloakAdminUserServiceImpl class");
		return keycloakAdminUserRepository.save(keycloakAdminUser);
	}

	@Override
	public List<KeycloakAdminUser> getAll(Pageable pageable) {
		LOGGER.info("Enter getAll() in KeycloakAdminUserServiceImpl class");
		Page<KeycloakAdminUser> page = keycloakAdminUserRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public KeycloakAdminUser getById(String id) {
		LOGGER.info("Enter getById() in KeycloakAdminUserServiceImpl class");
		KeycloakAdminUser keycloakAdminUser = null;
		if (StringUtils.isNotBlank(id)) {
			String trimedId = id.trim();
			Optional<KeycloakAdminUser> optional = keycloakAdminUserRepository.findById(trimedId);
			if (optional.isPresent()) {
				keycloakAdminUser = optional.get();
			}
		}
		return keycloakAdminUser;
	}

	@Override
	public void deleteById(String id) {
		LOGGER.info("Enter deleteById() in KeycloakAdminUserServiceImpl class");
		if (StringUtils.isNotBlank(id)) {
			String trimedId = id.trim();
			Optional<KeycloakAdminUser> optional = keycloakAdminUserRepository.findById(trimedId);
			if (optional.isPresent()) {
				keycloakAdminUserRepository.deleteById(trimedId);
			}
		}
	}

	@Override
	public void deleteAll() {
		LOGGER.info("Enter deleteAll() in KeycloakAdminUserServiceImpl class");
		this.keycloakAdminUserRepository.deleteAll();
	}

	@Override
	public void syncKeycloak() {
		LOGGER.info("Enter syncKeycloak() in KeycloakAdminUserServiceImpl class");
		Set<UserRepresentation> users = this.keycloakService.getAllEnabledUsers();
		saveKeycloakAdminUser(users);
		Set<UserRepresentation> admins = this.keycloakService.getAllEnabledAdmins();
		saveKeycloakAdminUser(admins);
	}

	private void saveKeycloakAdminUser(Set<UserRepresentation> userRepresentations) {
		LOGGER.info("Enter saveKeycloakAdminUser() in KeycloakAdminUserServiceImpl class");
		for (UserRepresentation userRepresentation : userRepresentations) {
			KeycloakAdminUser keycloakAdminUser = new KeycloakAdminUser();
			keycloakAdminUser.setId(userRepresentation.getId());
			keycloakAdminUser.setEmail(userRepresentation.getEmail());
			keycloakAdminUser.setFirstName(userRepresentation.getFirstName());
			keycloakAdminUser.setLastName(userRepresentation.getLastName());
			this.keycloakAdminUserRepository.save(keycloakAdminUser);
		}
	}

}
