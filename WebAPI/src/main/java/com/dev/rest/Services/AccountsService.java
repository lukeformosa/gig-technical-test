package com.dev.rest.Services;

import com.dev.rest.Models.AccountsTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.rest.Repositories.AccountsRepository;
import java.util.List;

@Service
public class AccountsService {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public List<AccountsTable> getAccounts() {
        return accountsRepository.findAll();
    }
}