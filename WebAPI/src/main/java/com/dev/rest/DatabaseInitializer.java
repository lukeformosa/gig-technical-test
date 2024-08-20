package com.dev.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;

@SuppressWarnings("ALL")
public class DatabaseInitializer {
    public static void initializeDatabase() {

        // To be used by Docker
        String dbUrl = System.getenv("DB_URL");
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/money_transfer_db", "postgres", "admin");
             Statement statement = connection.createStatement()) {

            String sql = new String(Files.readAllBytes(Paths.get("src/main/resources/data.sql")));
            statement.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Use this for Docker later on

//        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
//             Statement statement = connection.createStatement()) {
//
//            String sql = new String(Files.readAllBytes(Paths.get("src/main/resources/data.sql")));
//            statement.execute(sql);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}