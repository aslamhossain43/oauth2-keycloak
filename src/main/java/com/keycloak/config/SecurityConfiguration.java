package com.keycloak.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import com.keycloak.security.KeycloakRealmRoleConverter;
import com.keycloak.util.keycloak.KeycloakAdapter;

/**
 *
 * @author Md. Aslam Hossain
 * @version 05/09/2021
 *
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	KeycloakAdapter keycloakAdapter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/keycloak/**").hasAuthority("ROLE_ADMIN").antMatchers("/**")
				.authenticated().and().oauth2ResourceServer().jwt()
				.jwtAuthenticationConverter(authenticationConverter()).and().and().oauth2Client();

	}

	Converter<Jwt, AbstractAuthenticationToken> authenticationConverter() {
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
		return jwtAuthenticationConverter;
	}

	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withJwkSetUri(keycloakAdapter.getJwkSetUri()).build();
	}

}
