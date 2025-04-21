package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.restControllers;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Customer;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    private final CustomerService customerService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerRestController.class);

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable Long customerId) {
        return customerService.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        try {
            Customer createdCustomer = customerService.registerCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
        } catch (Exception e) {
            logger.error("Error creating customer: ", e);
            String errorMessage = "Error adding customer: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PutMapping("/{customerId}")
    public Customer updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
        logger.info("Updating customer with ID: " + customerId);
        return customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }
}