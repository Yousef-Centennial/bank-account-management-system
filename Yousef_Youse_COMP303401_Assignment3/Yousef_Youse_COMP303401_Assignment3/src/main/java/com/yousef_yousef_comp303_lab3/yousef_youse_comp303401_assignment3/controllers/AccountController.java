package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.controllers;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Account;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.AccountType;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.BankManager;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Customer;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/manager/accounts")
public class AccountController {


    private final RestTemplate restTemplate;
    private static final Logger logger = Logger.getLogger(AccountController.class.getName());

    private static final String BASE_URL = "http://localhost:60890/api";

    public AccountController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/add")
    public String addAccount(@RequestParam Long customerId,
                             @RequestParam Long accountTypeId,
                             @RequestParam BigDecimal balance,
                             @RequestParam BigDecimal overDraftLimit) {
        Account account = new Account();
        account.setCustomer(new Customer());
        account.getCustomer().setCustomerId(customerId);
        account.setAccountType(new AccountType());
        account.getAccountType().setAccountTypeId(accountTypeId);
        account.setBalance(balance);
        account.setOverDraftLimit(overDraftLimit);

        restTemplate.postForObject(BASE_URL + "/accounts", account, Account.class);
        return "redirect:/manager/home?success";
    }

    @PostMapping("/update")
    public String updateAccount(@RequestParam Long accountNumber,
                                @RequestParam BigDecimal balance,
                                @RequestParam BigDecimal overDraftLimit) {
        try {
            String url = BASE_URL + "/accounts/" + accountNumber;

            // Create a new account object with just the fields to update
            Account updateData = new Account();
            updateData.setAccountNumber(accountNumber);
            updateData.setBalance(balance);
            updateData.setOverDraftLimit(overDraftLimit);

            // Send the update
            restTemplate.put(url, updateData);
            return "redirect:/manager/home?success";
        } catch (Exception e) {
            logger.severe("Error updating account: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/manager/home?error=updateFailed";
        }
    }

    @PostMapping("/delete")
    public String deleteAccount(@RequestParam Long accountNumber) {
        restTemplate.delete(BASE_URL + "/accounts/" + accountNumber);
        return "redirect:/manager/home?success";
    }

    @GetMapping
    public String viewAccounts(Model model, HttpSession session) {
        List<Account> accounts = restTemplate.getForObject(BASE_URL + "/accounts", List.class);
        List<AccountType> accountTypes = restTemplate.getForObject(BASE_URL + "/accountTypes", List.class);
        List<Customer> customers = restTemplate.getForObject(BASE_URL + "/customers", List.class);

        BankManager loggedInManager = (BankManager) session.getAttribute("loggedInManager");

        model.addAttribute("accounts", accounts);
        model.addAttribute("accountTypes", accountTypes);
        model.addAttribute("customers", customers);
        model.addAttribute("loggedInManager", loggedInManager);

        return "manager/accounts";
    }
}