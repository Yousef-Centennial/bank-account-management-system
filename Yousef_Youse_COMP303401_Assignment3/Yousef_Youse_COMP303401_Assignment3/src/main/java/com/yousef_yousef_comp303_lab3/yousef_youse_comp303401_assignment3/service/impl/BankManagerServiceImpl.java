package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.impl;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.BankManager;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.repositories.BankManagerRepository;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.BankManagerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

// BankManager service implementation
@Service
public class BankManagerServiceImpl implements BankManagerService {
    private final BankManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    public BankManagerServiceImpl(BankManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public BankManager registerManager(BankManager manager) {
        // Encode the password before saving
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        return managerRepository.save(manager);
    }

    @Override
    public Optional<BankManager> findByUsername(String username) {
        return managerRepository.findByUsername(username);
    }

    @Override
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}