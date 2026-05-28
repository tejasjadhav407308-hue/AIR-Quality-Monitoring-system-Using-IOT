package com.bank.bank_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank_system.model.Account;
import com.bank.bank_system.model.Kyc;
import com.bank.bank_system.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    // CREATE ACCOUNT
    public Account createAccount(Account account) {

        // Auto-generate required fields (IMPORTANT FIX)
        account.setAccountNumber("ACC" + System.currentTimeMillis());
        account.setCustomerId("CUST" + System.currentTimeMillis());

        return repository.save(account);
    }

    // GET ALL ACCOUNTS
    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    // GET ACCOUNT BY ID
    public Account getAccount(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    // TRANSACTION CHECK
    public String checkTransaction(double amount) {
        if (amount > 50000) {
            return "PAN REQUIRED";
        }
        return "APPROVED";
    }

    // RISK CHECK
    public String checkRisk(double amount) {

        int score = 0;

        if (amount > 50000) {
            score += 40;
        }

        if (amount > 100000) {
            score += 30;
        }

        if (score > 70) {
            return "HIGH RISK - BLOCK";
        } else if (score > 30) {
            return "MEDIUM RISK - REVIEW";
        } else {
            return "LOW RISK - APPROVED";
        }
    }

    // KYC PROCESS
    public String processKyc(Kyc kyc) {

        if (kyc.getPan() == null || kyc.getPan().isEmpty()) {
            return "PAN REQUIRED";
        }

        if (kyc.getAadhaar() == null || kyc.getAadhaar().isEmpty()) {
            return "AADHAAR REQUIRED";
        }

        return "KYC SUCCESSFUL";
    }

public Account deposit(Long id, double amount) {

    Account account = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Account not found"));

    account.setBalance(account.getBalance() + amount);

    return repository.save(account);
}
public Account withdraw(Long id, double amount) {

    Account account = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Account not found"));

    if (account.getBalance() < amount) {
        throw new RuntimeException("Insufficient Balance");
    }

    account.setBalance(account.getBalance() - amount);

    return repository.save(account);
}
public String transfer(Long fromId, Long toId, double amount) {

    Account sender = repository.findById(fromId)
            .orElseThrow(() -> new RuntimeException("Sender not found"));

    Account receiver = repository.findById(toId)
            .orElseThrow(() -> new RuntimeException("Receiver not found"));

    if (sender.getBalance() < amount) {
        return "Insufficient Balance";
    }

    sender.setBalance(sender.getBalance() - amount);

    receiver.setBalance(receiver.getBalance() + amount);

    repository.save(sender);
    repository.save(receiver);

    return "Transfer Successful";
}
}