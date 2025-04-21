package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.controllers;


import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.AccountType;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.AccountTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account-types")
public class AccountTypeController {
    private final AccountTypeService accountTypeService;

    public AccountTypeController(AccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }

    @PostMapping
    public ResponseEntity<AccountType> createAccountType(@RequestBody AccountType accountType) {
        return ResponseEntity.ok(accountTypeService.createAccountType(accountType));
    }

    @GetMapping
    public ResponseEntity<List<AccountType>> getAllAccountTypes() {
        return ResponseEntity.ok(accountTypeService.getAllAccountTypes());
    }

    @GetMapping("/{name}")
    public ResponseEntity<AccountType> getAccountTypeByName(@PathVariable String name) {
        return ResponseEntity.ok(accountTypeService.findByName(name));
    }
}