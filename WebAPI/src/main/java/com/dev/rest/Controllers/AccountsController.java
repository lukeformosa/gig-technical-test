package com.dev.rest.Controllers;

import com.dev.rest.Models.Account;
import com.dev.rest.Models.TransferBalanceModel;
import com.dev.rest.Models.TransferBalanceResponseModel;
import com.dev.rest.Services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountsController {

    private final AccountsService accountsService;

    @Autowired
    public AccountsController(AccountsService itemService) {
        this.accountsService = itemService;
    }

    //This method is created to make testing the API easier, this will provide the IDs in the database corresponding to user accounts.
    @GetMapping("/ids")
    public List<Integer> getIds(){
        return accountsService.getAllAccountIds();
    }

    @PostMapping("/transfer")
    public TransferBalanceResponseModel transferBalance(@RequestBody TransferBalanceModel tbm){
        return accountsService.transferBalance(tbm);
    }
}