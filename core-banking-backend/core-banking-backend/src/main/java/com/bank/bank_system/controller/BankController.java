package com.bank.bank_system.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bank_system.model.Account;
import com.bank.bank_system.model.Kyc;
import com.bank.bank_system.service.AccountService;

@RestController
@RequestMapping("/account")
public class BankController {

    @Autowired
    private AccountService service;

    // CREATE ACCOUNT
    @PostMapping
    public Account create(@RequestBody Account account) {
        return service.createAccount(account);
    }

    // GET ALL ACCOUNTS
    @GetMapping
    public List<Account> getAll() {
        return service.getAllAccounts();
    }

    // GET ACCOUNT BY ID
    @GetMapping("/{id}")
    public Account getById(@PathVariable Long id) {
        return service.getAccount(id);
    }
    // CHECK TRANSACTION (NEW API)
@PostMapping("/check")
public String checkTransaction(@RequestParam double amount) {
    return service.checkTransaction(amount);
}
@PostMapping("/risk")
public String checkRisk(@RequestParam double amount) {
    return service.checkRisk(amount);
}
@PostMapping("/kyc")
public String kyc(@RequestBody Kyc kyc) {
    return service.processKyc(kyc);
}
@PostMapping("/deposit")
public Account deposit(@RequestParam Long id,
                       @RequestParam double amount) {

    return service.deposit(id, amount);
}
@PostMapping("/withdraw")
public Account withdraw(@RequestParam Long id,
                        @RequestParam double amount) {

    return service.withdraw(id, amount);
}
@PostMapping("/transfer")
public String transfer(@RequestParam Long fromId,
                       @RequestParam Long toId,
                       @RequestParam double amount) {

    return service.transfer(fromId, toId, amount);
}
}