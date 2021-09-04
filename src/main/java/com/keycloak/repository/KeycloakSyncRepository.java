package com.keycloak.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.support.CypherdslConditionExecutor;
import org.springframework.data.neo4j.repository.support.CypherdslStatementExecutor;

import com.keycloak.model.KeycloakUser;

/**
 * Spring Data JPA repository for the {@link KeycloakUser} entity.
 */
public interface KeycloakSyncRepository extends Neo4jRepository<KeycloakUser, String>,
		CypherdslConditionExecutor<KeycloakUser>, CypherdslStatementExecutor<KeycloakUser> {
}
