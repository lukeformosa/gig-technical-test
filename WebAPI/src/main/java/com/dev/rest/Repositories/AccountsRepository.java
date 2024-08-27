package com.dev.rest.Repositories;

import com.dev.rest.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.balance = :balance WHERE a.id = :id")
    int updateBalance(@Param("id") Integer id, @Param("balance") double balance);

}
