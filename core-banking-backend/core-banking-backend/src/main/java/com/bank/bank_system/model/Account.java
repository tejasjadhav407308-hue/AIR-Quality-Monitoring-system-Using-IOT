package com.bank.bank_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Auto-generated account number (NOT sent by user)
    @Column(unique = true, nullable = false)
    private String accountNumber;

    // Auto-generated customer id (or later link to Customer entity)
    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String name;

    // SAVINGS / CURRENT
    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false)
    private double balance;

    public Account() {}

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}