package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.impl;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.repositories.AccountRepository;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final AccountRepository accountRepository;

    @Autowired
    public ReportServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Object[]> getAccountTypesByTotalBalance() {
        return accountRepository.findAccountTypesByTotalBalance();
    }

    @Override
    public List<Object[]> getAccountTypeCountsByCity() {
        return accountRepository.findAccountTypeCountsByCity();
    }

    @Override
    public List<String> getAccountTypesWithOverdraftLimitGreaterThan(BigDecimal amount) {
        return accountRepository.findAccountTypesWithHighOverdraftLimit();
    }
}