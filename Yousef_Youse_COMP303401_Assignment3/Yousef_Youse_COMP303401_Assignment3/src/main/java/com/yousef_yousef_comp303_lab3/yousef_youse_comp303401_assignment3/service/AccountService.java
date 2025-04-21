package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account createAccount(Account account);

    List<Account> getAccountsByCustomerId(Long customerId);

    Account getAccountById(Long accountNumber);

    Account updateAccount(Long accountId, Account account);

    void deleteAccount(Long accountId);

    List<Account> getAllAccounts();

    List<Object[]> findAccountTypeCountsByCity();

    List<Account> findAccountsWithOverdraftLimitGreaterThan(BigDecimal amount);
}