package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.BankManager;

import java.util.Optional;

public interface BankManagerService {
    BankManager registerManager(BankManager manager);

    Optional<BankManager> findByUsername(String username);

    boolean validatePassword(String rawPassword, String encodedPassword);
}

