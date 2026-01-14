package com.keelient.catalog_service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
@Profile("test")
@ConditionalOnClass(name = "org.junit.rules.TestRule")
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>(DockerImageName.parse("postgres:18-alpine"))
				.withDatabaseName("postgres")
				.withUsername("postgres")
				.withPassword("postgres")
				.withReuse(true); // Fondamentale per non far morire il container dopo ogni test
	}
}