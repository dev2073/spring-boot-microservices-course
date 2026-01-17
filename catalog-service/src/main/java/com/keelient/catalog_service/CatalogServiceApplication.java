package com.keelient.catalog_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }

    @Component
    public static class ProfileLogger implements CommandLineRunner {

        @Value("${spring.profiles.active:default}")
        private String activeProfile;

        @Override
        public void run(String... args) {
            System.out.println("=================================");
            System.out.println("Active Profilexx: " + activeProfile);
            System.out.println("=================================");
        }
    }
}
