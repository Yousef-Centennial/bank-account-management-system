package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.impl;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Account;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.exception.ResourceNotFoundException;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.repositories.AccountRepository;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerCustomerId(customerId);
    }

    public Account getAccountById(Long accountNumber) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        return account.orElse(null);
    }

    @Override
    public Account updateAccount(Long accountId, Account accountDetails) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + accountId));

        account.setBalance(accountDetails.getBalance());
        account.setOverDraftLimit(accountDetails.getOverDraftLimit());

        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + accountId));

        accountRepository.delete(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public List<Object[]> findAccountTypeCountsByCity() {
        return accountRepository.findAccountTypeCountsByCity();
    }

    @Override
    public List<Account> findAccountsWithOverdraftLimitGreaterThan(BigDecimal amount) {
        return accountRepository.findByOverDraftLimitGreaterThan(amount);
    }
}
