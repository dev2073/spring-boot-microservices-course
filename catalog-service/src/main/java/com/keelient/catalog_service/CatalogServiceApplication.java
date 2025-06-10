package com.keelient.catalog_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogServiceApplication {
    // ddddd
    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
        System.out.println("Catalog Service is running...");
    }
}
