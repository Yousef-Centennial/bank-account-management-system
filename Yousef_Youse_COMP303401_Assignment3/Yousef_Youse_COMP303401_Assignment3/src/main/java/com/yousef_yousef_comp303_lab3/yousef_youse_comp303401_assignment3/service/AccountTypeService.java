package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.AccountType;

import java.util.List;

public interface AccountTypeService {
    AccountType createAccountType(AccountType accountType);

    List<AccountType> getAllAccountTypes();

    AccountType findByName(String name);

    AccountType getAccountTypeById(Long id);
}