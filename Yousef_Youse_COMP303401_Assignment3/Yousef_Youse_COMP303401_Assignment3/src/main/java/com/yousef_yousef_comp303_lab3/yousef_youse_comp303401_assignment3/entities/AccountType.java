package com.yousef_yousef_comp303_lab3.yousef_youse_comp303401_assignment3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "account_type")
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_type_id")
    private Long accountTypeId;

    @NotBlank(message = "Account type name is required")
    @Column(name = "account_type_name", nullable = false)
    private String accountTypeName;

    @Column(name = "account_type_desc")
    private String accountTypeDesc;

    @DecimalMin(value = "0.0", message = "Minimum balance cannot be negative")
    @Column(name = "minimum_balance_amount")
    private BigDecimal minimumBalanceAmount;

    @Column(name = "has_over_draft")
    private Boolean hasOverDraft;

    @OneToMany(mappedBy = "accountType", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();
}
