package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.controllers;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/manager/customers")
public class CustomerController {
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private static final String BASE_URL = "http://localhost:60890/api";

    public CustomerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer, Model model) {
        try {
            restTemplate.postForObject(BASE_URL + "/customers", customer, Customer.class);
            return "redirect:/manager/home?success";
        } catch (HttpClientErrorException e) {
            String errorMessage = "Error adding customer: " + e.getStatusCode() + " on POST request for \"" + BASE_URL + "/customers\": \"" + e.getResponseBodyAsString() + "\"";
            model.addAttribute("errorMessage", errorMessage);
            return "manager/addCustomerForm";
        }
    }

    @PostMapping("/update")
    public String updateCustomerFromForm(@ModelAttribute Customer customer, Model model) {
        try {
            String url = BASE_URL + "/customers/" + customer.getCustomerId();
            restTemplate.put(url, customer);
            return "redirect:/manager/home?success";
        } catch (Exception e) {
            logger.error("Error updating customer: {}", e.getMessage());
            return "redirect:/manager/home?error=updateFailed";
        }
    }

    @PostMapping("/delete")
    public String deleteCustomerFromForm(@RequestParam Long customerId, Model model) {
        try {
            restTemplate.delete(BASE_URL + "/customers/" + customerId);
            return "redirect:/manager/home?success";
        } catch (Exception e) {
            logger.error("Error deleting customer: {}", e.getMessage());
            return "redirect:/manager/home?error=deleteFailed";
        }
    }

    @GetMapping
    public String viewCustomers(Model model) {
        try {
            List<Customer> customers = restTemplate.getForObject(BASE_URL + "/customers", List.class);
            model.addAttribute("customers", customers);
            return "manager/customers";
        } catch (Exception e) {
            logger.error("Error fetching customers: {}", e.getMessage());
            model.addAttribute("error", "Failed to load customers");
            return "manager/customers";
        }
    }
}