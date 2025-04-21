package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer registerCustomer(Customer customer);

    Optional<Customer> findByUsername(String username);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Long customerId, Customer customer);

    void deleteCustomer(Long customerId);

    boolean validatePassword(String rawPassword, String encodedPassword);

    Optional<Customer> findById(Long customerId);
}
