package com.codecool.stackoverflowtw.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    @Value( "${password}" )
    String PASSWORD;
    @Bean
    public Database getDatabase() {
        return new Database("jdbc:postgresql://localhost:5432/askmate",
                "postgres",
                PASSWORD);
    }
}
