package com.keycloak.util.keycloak;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakAdapter {

	@Value("${spring.security.oauth2.host.keycloak.url}")
	private String url;

	@Value("${spring.security.oauth2.host.keycloak.realm}")
	private String realm;

	@Value("${spring.security.oauth2.host.keycloak.client-id}")
	private String clientId;

	@Value("${spring.security.oauth2.host.keycloak.client-secret}")
	private String clientSecret;

	@Value("${spring.security.oauth2.host.keycloak.username}")
	private String username;

	@Value("${spring.security.oauth2.host.keycloak.password}")
	private String password;

	public Keycloak createKeycloakBuilder() {

		return KeycloakBuilder.builder().serverUrl(url).grantType(OAuth2Constants.CLIENT_CREDENTIALS).realm("trakti")
				.clientId(clientId).clientSecret(
						clientSecret)/* .username(username).password(password) */
				.resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
	}
}
