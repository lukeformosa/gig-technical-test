package com.dev.rest.Services;

import com.dev.rest.Models.Account;
import com.dev.rest.Models.TransferBalanceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.rest.Repositories.AccountsRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountsService {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public List<Account> getAccounts() {
        return accountsRepository.findAll();
    }

    public String transferBalance(TransferBalanceModel tbm){

        Account sender = getAccount(tbm.getSenderId());

        if(sender == null){
            return "Operation failed, sender was not found.";
        }

        Account receiver = getAccount(tbm.getReceiverId());

        if(receiver == null){
            return "Operation failed, receiver was not found.";
        }

        if(tbm.getTransferAmount() > sender.getBalance()){
            return "Operation failed, transfer amount cannot be greater than account balance.";
        }

        //Deduct balance from sender
        accountsRepository.updateBalance(sender.getId(), sender.getBalance() - tbm.getTransferAmount());

        //Add balance to receiver
        accountsRepository.updateBalance(receiver.getId(), receiver.getBalance() + tbm.getTransferAmount());

        return "Operation successful, balance updated.";
    }

    public Account getAccount(Integer id){
        Optional<Account> fetchedAccount = accountsRepository.findById(id);
        return fetchedAccount.orElse(null);
    }


    //This method is created to make testing the API easier, this will provide the IDs in the database corresponding to user accounts.
    public List<Integer> getAllAccountIds() {
        return accountsRepository.findAll()
                .stream()
                .map(Account::getId)
                .collect(Collectors.toList());
    }
}