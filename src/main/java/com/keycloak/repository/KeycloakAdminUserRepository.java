package com.keycloak.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.support.CypherdslConditionExecutor;
import org.springframework.data.neo4j.repository.support.CypherdslStatementExecutor;

import com.keycloak.model.KeycloakAdminUser;

/**
 *
 * @author Md. Aslam Hossain
 * @version 05/09/2021
 *
 */
public interface KeycloakAdminUserRepository extends Neo4jRepository<KeycloakAdminUser, String>,
		CypherdslConditionExecutor<KeycloakAdminUser>, CypherdslStatementExecutor<KeycloakAdminUser> {
}
