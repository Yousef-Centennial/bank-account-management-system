package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.controllers;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Account;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Customer;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.AccountService;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.AccountTypeService;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/manager")
public class ManagerController {
    private final CustomerService customerService;
    private final AccountService accountService;
    private final AccountTypeService accountTypeService;

    public ManagerController(CustomerService customerService, AccountService accountService, AccountTypeService accountTypeService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.accountTypeService = accountTypeService;
    }

    // Constructor injection

    @GetMapping("/home")
    public String showManagerHome(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        Map<Long, List<Account>> customerAccounts = new HashMap<>();

        for (Customer customer : customers) {
            customerAccounts.put(customer.getCustomerId(),
                    accountService.getAccountsByCustomerId(customer.getCustomerId()));
        }

        model.addAttribute("customers", customers);
        model.addAttribute("customerAccounts", customerAccounts);
        model.addAttribute("accountTypes", accountTypeService.getAllAccountTypes());

        return "manager/home";
    }
}