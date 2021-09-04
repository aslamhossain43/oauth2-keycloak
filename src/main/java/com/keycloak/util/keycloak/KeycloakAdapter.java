package com.keycloak.util.keycloak;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Md. Aslam Hossain
 * @version 05/09/2021
 *
 */
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
	@Value("${spring.security.oauth2.client.provider.oidc.jwk-set-uri}")
	private String jwkSetUri;

	public String getUrl() {
		return url;
	}

	public String getRealm() {
		return realm;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public String getJwkSetUri() {
		return jwkSetUri;
	}

	public Keycloak createKeycloakBuilder() {
		return KeycloakBuilder.builder().serverUrl(url).grantType(OAuth2Constants.CLIENT_CREDENTIALS).realm(realm)
				.clientId(clientId).clientSecret(clientSecret)
				.resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
	}
}
