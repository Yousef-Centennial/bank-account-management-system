package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.restControllers;

import com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportRestController {

    private final ReportService reportService;
    private static final Logger logger = LoggerFactory.getLogger(ReportRestController.class);

    public ReportRestController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/accountTypesByBalance")
    public List<Object[]> getAccountTypesByTotalBalance() {
        return reportService.getAccountTypesByTotalBalance();
    }

    @GetMapping("/accountTypesByCity")
    public List<Object[]> getAccountTypeCountsByCity() {
        return reportService.getAccountTypeCountsByCity();
    }

    @GetMapping("/highOverdraftTypes")
    public List<String> getHighOverdraftTypes() {
        return reportService.getAccountTypesWithOverdraftLimitGreaterThan(
                new BigDecimal("1000.00")
        );
    }
}