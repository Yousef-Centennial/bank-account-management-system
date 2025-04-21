package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.restControllers;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Account;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {


    private final AccountService accountService;

    private static final Logger logger = LoggerFactory.getLogger(AccountRestController.class);

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{accountNumber}")
    public Account getAccount(@PathVariable Long accountNumber) {
        return accountService.getAccountById(accountNumber);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping("/{accountNumber}")
    public Account updateAccount(@PathVariable Long accountNumber, @RequestBody Account account) {

        logger.info("Updating account with account number: " + accountNumber);
        return accountService.updateAccount(accountNumber, account);
    }

    @DeleteMapping("/{accountNumber}")
    public void deleteAccount(@PathVariable Long accountNumber) {
        accountService.deleteAccount(accountNumber);
    }
}