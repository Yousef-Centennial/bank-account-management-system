package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.repositories;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    // Find account type by name
    AccountType findByAccountTypeName(String accountTypeName);
}
