package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.controllers;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.Customer;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerProfileController {

    private final CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerProfileController.class);

    public CustomerProfileController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/profile")
    public String showProfilePage(HttpSession session, Model model) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");

        logger.info("Customer Profile Page Accessed {}", loggedInUser.getCustomerId());

        model.addAttribute("customer", loggedInUser);
        return "customer/profile"; // Display the profile page
    }

    @PostMapping("/customer/profile/update")
    public String updateProfile(@RequestParam String customerName,
                                @RequestParam String emailId,
                                @RequestParam String phone,
                                @RequestParam String address,
                                HttpSession session, RedirectAttributes redirectAttributes) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            loggedInUser.setCustomerName(customerName);
            loggedInUser.setEmailId(emailId);
            loggedInUser.setPhone(phone);
            loggedInUser.setAddress(address);

            customerService.updateCustomer(loggedInUser.getCustomerId(), loggedInUser);

            session.setAttribute("loggedInUser", loggedInUser); // Update the session

            redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
        }

        return "redirect:/customer/profile";
    }

}
