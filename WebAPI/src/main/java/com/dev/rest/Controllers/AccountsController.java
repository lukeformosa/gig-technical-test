package com.dev.rest.Controllers;

import com.dev.rest.Models.AccountsTable;
import com.dev.rest.Services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/")
public class AccountsController {

    private final AccountsService accountsService;

    @Autowired
    public AccountsController(AccountsService itemService) {
        this.accountsService = itemService;
    }

    @GetMapping("accounts")
    public List<AccountsTable> getAccounts() {
        return accountsService.getAccounts();
    }
}