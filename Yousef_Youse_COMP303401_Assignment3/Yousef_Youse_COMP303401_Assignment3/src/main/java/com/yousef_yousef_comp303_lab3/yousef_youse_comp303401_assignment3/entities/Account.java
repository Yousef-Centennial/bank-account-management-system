package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "account")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private Long accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @DecimalMin(value = "0.0", message = "Balance cannot be negative")
    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @DecimalMin(value = "0.0", message = "Overdraft limit cannot be negative")
    @Column(name = "over_draft_limit")
    private BigDecimal overDraftLimit = BigDecimal.ZERO;
}
