package com.keelient.catalog_service.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.keelient.catalog_service.TestcontainersConfiguration;
import com.keelient.catalog_service.domains.ProductEntity;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

/**
 *
 * Test di integrazione per ProductRepository.
 *
 * <p>
 *
 *
 * Utilizza @DataJpaTest per testare solo lo strato dati con un contesto Spring
 * leggero e un database PostgreSQL gestito da testcontainer.
 *
 * <p>
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * Configurazione:
 * - spring.test.database.replace=none: NON sostituire con un DB H2 in-memory.
 * Utilizzare
 * il PostgreSQL configurato invece.
 * - spring.datasource.url=jdbc:tc:postgres:18-alpine:///db: Utilizzare
 * Testcontainers
 * per avviare automaticamente un container PostgreSQL 18-alpine per questo
 * test. Il
 * prefisso jdbc:tc: indica a Spring di utilizzare il driver JDBC di
 * Testcontainers.
 */
@DataJpaTest(
        properties = {
            // Do NOT use H2 in-memory database; use configured PostgreSQL Testcontainer
            "spring.test.database.replace=none",
        })
@Sql(scripts = "/test-data.sql")
@ActiveProfiles("test")
@DisplayName("Test di Integrazione per ProductRepository")
@Import(TestcontainersConfiguration.class)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        assertThat(products).hasSize(15);
    }
}
