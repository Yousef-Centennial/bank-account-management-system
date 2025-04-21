package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.repositories;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.BankManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankManagerRepository extends JpaRepository<BankManager, Long> {
    Optional<BankManager> findByUsername(String username);
}