package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.controllers;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities.BankManager;
import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.BankManagerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ManagerLoginController {
    private final BankManagerService managerService;

    public ManagerLoginController(BankManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // Use your existing login template
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Optional<BankManager> manager = managerService.findByUsername(username);

        if (manager.isPresent() && managerService.validatePassword(password, manager.get().getPassword())) {
            session.setAttribute("loggedInManager", manager.get());
            return "redirect:/manager/home";  // Redirect to manager home page
        } else {
            return "redirect:/login?error";  // Show error
        }
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";  // Use your existing register template
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String fullName,
                           @RequestParam String email) {
        // Check if username already exists
        if (managerService.findByUsername(username).isPresent()) {
            return "redirect:/register?error=Username already exists";
        }

        // Create new manager
        BankManager manager = new BankManager();
        manager.setUsername(username);
        manager.setPassword(password);
        manager.setFullName(fullName);
        manager.setEmail(email);

        managerService.registerManager(manager);
        return "redirect:/login?success=Registration successful";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}