package com.keycloak.controller.keycloak;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Md. Aslam Hossain
 * @version 05/09/2021
 *
 */
@RestController
@RequestMapping("/api/logout")
public class LogoutController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);
	private final ClientRegistration registration;

	public LogoutController(ClientRegistrationRepository registrations) {
		this.registration = registrations.findByRegistrationId("oidc");
	}

	@PostMapping
	public ResponseEntity<Map<String, String>> logout(HttpServletRequest request) {
		LOGGER.info("Enter logout() in LogoutController class");
		String logoutUrl = this.registration.getProviderDetails().getConfigurationMetadata().get("end_session_endpoint")
				.toString();
		Map<String, String> logoutDetails = new HashMap<>();
		logoutDetails.put("logoutUrl", logoutUrl);
		request.getSession().invalidate();
		return ResponseEntity.ok().body(logoutDetails);
	}
}
