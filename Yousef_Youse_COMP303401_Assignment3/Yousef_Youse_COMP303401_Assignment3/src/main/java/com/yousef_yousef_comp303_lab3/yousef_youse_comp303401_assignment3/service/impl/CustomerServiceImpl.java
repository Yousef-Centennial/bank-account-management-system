package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.impl;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Customer;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.exception.ResourceNotFoundException;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.repositories.CustomerRepository;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    // Constructor injection for PasswordEncoder
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();  // Using BCrypt for password hashing
    }

    @Override
    public Customer registerCustomer(Customer customer) {
        // Hashing the password before saving the customer
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customerDetails) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));

        customer.setCustomerName(customerDetails.getCustomerName());
        customer.setCity(customerDetails.getCity());
        customer.setEmailId(customerDetails.getEmailId());
        customer.setPhone(customerDetails.getPhone());
        customer.setPostalcode(customerDetails.getPostalcode());
        customer.setAddress(customerDetails.getAddress());
        customer.setDob(customerDetails.getDob());

        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));

        customerRepository.delete(customer);
    }

    public Optional<Customer> findById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    // Method to validate password during login
    public boolean validatePassword(String plainPassword, String storedPasswordHash) {
        return passwordEncoder.matches(plainPassword, storedPasswordHash);
    }
}
