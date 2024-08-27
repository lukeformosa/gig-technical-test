package com.dev.rest;

import com.dev.rest.Models.Account;
import com.dev.rest.Repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;

@SuppressWarnings("ALL")
@Component
public class DatabaseInitializer {

    @Autowired
    private AccountsRepository accountRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Dummy data
            accountRepository.save(new Account(1, "Luke", "Formosa", 1000.00));
            accountRepository.save(new Account(2, "Jane", "Mifsud", 2000.00));
            accountRepository.save(new Account(3, "Karl", "Testa", 1500.00));
            accountRepository.save(new Account(4, "Jack", "Wright", 3000.00));
        };
    }
}