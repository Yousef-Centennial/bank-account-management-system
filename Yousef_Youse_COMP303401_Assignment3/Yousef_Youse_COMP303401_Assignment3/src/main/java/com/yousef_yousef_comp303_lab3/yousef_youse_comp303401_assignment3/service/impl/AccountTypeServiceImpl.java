package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.impl;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.AccountType;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.repositories.AccountTypeRepository;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.AccountTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {
    private final AccountTypeRepository accountTypeRepository;

    public AccountTypeServiceImpl(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public AccountType createAccountType(AccountType accountType) {
        return accountTypeRepository.save(accountType);
    }

    @Override
    public List<AccountType> getAllAccountTypes() {
        return accountTypeRepository.findAll();
    }

    @Override
    public AccountType findByName(String name) {
        return accountTypeRepository.findByAccountTypeName(name);
    }

    public AccountType getAccountTypeById(Long accountTypeId) {
        Optional<AccountType> accountType = accountTypeRepository.findById(accountTypeId);
        return accountType.orElse(null);
    }
}
