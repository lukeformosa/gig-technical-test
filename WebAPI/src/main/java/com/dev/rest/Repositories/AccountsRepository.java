package com.dev.rest.Repositories;

import com.dev.rest.Models.AccountsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<AccountsTable, Long> {
}
