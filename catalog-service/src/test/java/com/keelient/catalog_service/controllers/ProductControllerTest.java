package com.keelient.catalog_service.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.keelient.catalog_service.AbstractIT;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

/**
 * Integration tests for ProductController API endpoints.
 *
 * <p>
 * Extends AbstractIT to inherit full Spring context with PostgreSQL
 * testcontainer database,
 * allowing real HTTP requests against the running application.
 */
@Sql(scripts = "/test-data.sql")
@DisplayName("Test di Integrazione per ProductController")
public class ProductControllerTest extends AbstractIT {

    /**
     * Test: GET /api/products should return paginated products.
     *
     * <p>
     * This integration test verifies the complete flow:
     * 1. Sends HTTP GET request to /api/products endpoint
     * 2. Verifies HTTP 200 OK response
     * 3. Validates paginated response structure
     * 4. Confirms first page contains 10 products from 15 total
     *
     * <p>
     * Uses RestAssured fluent API for readable BDD-style assertions.
     */
    @Test
    void shouldReturnProducts() {
        // Given: Set request content type to JSON
        // When: Make GET request to /api/products
        // Then: Verify response matches expected structure
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                // Verify HTTP status code is 200 (OK)
                .statusCode(200)
                // Verify data array contains exactly 10 products (page size)
                .body("data", hasSize(10))
                // Verify total 15 products exist in database
                .body("totalElements", is(15))
                // Verify current page is 1
                .body("pageNumber", is(1))
                // Verify there are 2 total pages (15 products / 10 per page)
                .body("totalPages", is(2))
                // Verify this is the first page
                .body("isFirst", is(true))
                // Verify this is not the last page
                .body("isLast", is(false))
                // Verify next page exists
                .body("hasNext", is(true))
                // Verify no previous page exists
                .body("hasPrevious", is(false));
    }
}
