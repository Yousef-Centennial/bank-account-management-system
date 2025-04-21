package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.repositories;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Find customer by username for login
    Optional<Customer> findByUsername(String username);

    // Find customers by city
    List<Customer> findByCity(String city);

    // Check if username exists
    boolean existsByUsername(String username);

    // Check if email exists
    boolean existsByEmailId(String emailId);
}