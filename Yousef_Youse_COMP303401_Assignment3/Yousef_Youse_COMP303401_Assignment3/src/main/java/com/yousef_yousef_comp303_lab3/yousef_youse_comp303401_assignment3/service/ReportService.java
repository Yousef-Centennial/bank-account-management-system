package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service;

import java.math.BigDecimal;
import java.util.List;

public interface ReportService {
    // Account types by total balance
    List<Object[]> getAccountTypesByTotalBalance();

    // Account type counts by city
    List<Object[]> getAccountTypeCountsByCity();

    // Account types with overdraft limit > specified amount
    List<String> getAccountTypesWithOverdraftLimitGreaterThan(BigDecimal amount);
}