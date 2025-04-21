package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.repositories;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Account;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomer(Customer customer);
    // Find accounts by customer ID
    List<Account> findByCustomerCustomerId(Long customerId);

    // Analytics query 1: Account types aggregated by total balance
    @Query("SELECT at.accountTypeName, SUM(a.balance) FROM Account a JOIN a.accountType at GROUP BY at.accountTypeName HAVING SUM(a.balance) > 0 ORDER BY SUM(a.balance) DESC")
    List<Object[]> findAccountTypesByTotalBalance();

    // Analytics query 2: Account type counts by customer's city
    @Query("SELECT c.city, at.accountTypeName, COUNT(a.accountNumber) FROM Account a JOIN a.customer c JOIN a.accountType at GROUP BY c.city, at.accountTypeName ORDER BY c.city, COUNT(a.accountNumber) DESC")
    List<Object[]> findAccountTypeCountsByCity();

    // Analytics query 3: Account types with overdraft limit > 1000
    @Query("SELECT DISTINCT at.accountTypeName FROM AccountType at WHERE EXISTS (SELECT 1 FROM Account a WHERE a.accountType = at AND a.overDraftLimit > 1000)")
    List<String> findAccountTypesWithHighOverdraftLimit();


    // Find accounts with overdraft limit greater than specified amount
    List<Account> findByOverDraftLimitGreaterThan(BigDecimal amount);
}
