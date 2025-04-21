package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.controllers;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Account;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.AccountType;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Customer;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager")
public class ReportsController {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ReportsController.class);
    private static final String BASE_URL = "http://localhost:60890/api";

    public ReportsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/reports")
    public String showReportsPage(Model model, HttpSession session) {
        // Check if manager is logged in
        if (session.getAttribute("loggedInManager") == null) {
            return "redirect:/login";
        }

        try {
            // Fetch reports data through API calls
            ResponseEntity<List<Object[]>> accountTypesByBalanceResponse =
                    restTemplate.exchange(
                            BASE_URL + "/reports/accountTypesByBalance",
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<Object[]>>() {
                            }
                    );

            ResponseEntity<List<Object[]>> accountTypesByCityResponse =
                    restTemplate.exchange(
                            BASE_URL + "/reports/accountTypesByCity",
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<Object[]>>() {
                            }
                    );

            List<String> highOverdraftTypes =
                    restTemplate.getForObject(
                            BASE_URL + "/reports/highOverdraftTypes",
                            List.class
                    );

            // Add data to the model
            model.addAttribute("accountTypesByBalance", accountTypesByBalanceResponse.getBody());
            model.addAttribute("accountTypesByCity", accountTypesByCityResponse.getBody());
            model.addAttribute("highOverdraftTypes", highOverdraftTypes);

            return "manager/reports";
        } catch (Exception e) {
            logger.error("Error fetching report data: {}", e.getMessage());
            model.addAttribute("error", "Failed to load reports data");
            return "manager/reports";
        }
    }

    @GetMapping("/homenav")
    public String showManagerHome(Model model) {
        try {
            // Fetch customers
            List<Customer> customers =
                    restTemplate.getForObject(BASE_URL + "/customers", List.class);

            // Fetch account types
            List<AccountType> accountTypes =
                    restTemplate.getForObject(BASE_URL + "/accountTypes", List.class);

            // Create a map for customer accounts
            Map<Long, List<Account>> customerAccounts = new HashMap<>();

            // For each customer, get their accounts
            if (customers != null) {
                for (Object customerObj : customers) {
                    // Since we're getting a list of Maps, we need to extract the customerId
                    Map<String, Object> customerMap = (Map<String, Object>) customerObj;
                    Number customerId = (Number) customerMap.get("customerId");

                    if (customerId != null) {
                        ResponseEntity<List<Account>> accountsResponse =
                                restTemplate.exchange(
                                        BASE_URL + "/accounts/customer/" + customerId.longValue(),
                                        HttpMethod.GET,
                                        null,
                                        new ParameterizedTypeReference<List<Account>>() {
                                        }
                                );

                        customerAccounts.put(customerId.longValue(), accountsResponse.getBody());
                    }
                }
            }

            model.addAttribute("customers", customers);
            model.addAttribute("customerAccounts", customerAccounts);
            model.addAttribute("accountTypes", accountTypes);

            return "manager/home";
        } catch (Exception e) {
            logger.error("Error loading home page data: {}", e.getMessage());
            model.addAttribute("error", "Failed to load data");
            return "manager/home";
        }
    }
}