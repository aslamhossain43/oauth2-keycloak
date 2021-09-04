package com.keycloak.controller.keycloak;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keycloak.model.KeycloakAdminUser;
import com.keycloak.service.KeycloakAdminUserService;

/**
 *
 * @author Md. Aslam Hossain
 * @version 05/09/2021
 *
 */
@RestController
@RequestMapping("/api/keycloak/adminuser")
public class KeycloakAdminUserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakAdminUserController.class);
	@Autowired
	private KeycloakAdminUserService keycloakAdminUserService;

	@GetMapping
	public ResponseEntity<List<KeycloakAdminUser>> getAll(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "per_page", required = false) String size) {
		LOGGER.info("Enter getAll() in KeycloakAdminUserController class");
		PageRequest pageRequest = PageRequest.of(page != null ? Integer.valueOf(page) : 0,
				size != null ? Integer.valueOf(size) : 20);
		return ResponseEntity.ok().body(keycloakAdminUserService.getAll(pageRequest));
	}

	@GetMapping("/{uuid}")
	ResponseEntity<KeycloakAdminUser> getById(@PathVariable(value = "uuid") String id) {
		LOGGER.info("Enter getById() in KeycloakAdminUserController class");
		return ResponseEntity.ok().body(keycloakAdminUserService.getById(id));
	}

	@DeleteMapping("/{uuid}")
	ResponseEntity<String> deleteById(@PathVariable(value = "uuid") String id) {
		LOGGER.info("Enter deleteById() in KeycloakAdminUserController class");
		keycloakAdminUserService.deleteById(id);
		return ResponseEntity.ok().body("deleted successfully id: " + id);
	}

	@DeleteMapping
	ResponseEntity<String> deleteAll() {
		LOGGER.info("Enter deleteAll() in KeycloakAdminUserController class");
		keycloakAdminUserService.deleteAll();
		return ResponseEntity.ok().body("deleted all successfully");
	}
}
