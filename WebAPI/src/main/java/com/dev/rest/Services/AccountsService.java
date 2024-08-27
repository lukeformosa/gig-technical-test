package com.dev.rest.Services;

import com.dev.rest.Models.Account;
import com.dev.rest.Models.TransferBalanceModel;
import com.dev.rest.Models.TransferBalanceResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.rest.Repositories.AccountsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Math.round;

@Service
public class AccountsService {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public TransferBalanceResponseModel transferBalance(TransferBalanceModel tbm) {

        if (tbm.getSenderId() == null) {
            return new TransferBalanceResponseModel(false, "senderId is a required field.");
        }

        if (tbm.getReceiverId() == null) {
            return new TransferBalanceResponseModel(false, "receiverId is a required field.");
        }

        if (tbm.getSenderId().equals(tbm.getReceiverId())) {
            return new TransferBalanceResponseModel(false, "sender and receiver cannot be the same user.");
        }

        Account sender = getAccount(tbm.getSenderId());

        if (sender == null) {
            return new TransferBalanceResponseModel(false, "sender not found.");
        }

        Account receiver = getAccount(tbm.getReceiverId());

        if (receiver == null) {
            return new TransferBalanceResponseModel(false, "receiver not found.");
        }

        if (tbm.getTransferAmount() > sender.getBalance()) {
            return new TransferBalanceResponseModel(false, "transfer amount cannot be greater than account balance.");
        }

        double senderOriginalBalance = sender.getBalance();
        double senderNewBalance = sender.getBalance() - tbm.getTransferAmount();
        double receiverOriginalBalance = receiver.getBalance();
        double receiverNewBalance = receiver.getBalance() + tbm.getTransferAmount();

        //Deduct balance from sender
        accountsRepository.updateBalance(sender.getId(), sender.getBalance() - tbm.getTransferAmount());

        //Add balance to receiver
        accountsRepository.updateBalance(receiver.getId(), receiver.getBalance() + tbm.getTransferAmount());

        return new TransferBalanceResponseModel(true, "Balance: " + String.format("%.2f", senderOriginalBalance) + " -> " + String.format("%.2f", senderNewBalance), "Balance: " + String.format("%.2f", receiverOriginalBalance) + " -> " + String.format("%.2f", receiverNewBalance));
    }

    private Account getAccount(Integer id) {
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