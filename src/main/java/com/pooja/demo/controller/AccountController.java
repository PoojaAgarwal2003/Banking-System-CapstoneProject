package com.pooja.demo.controller;

import com.pooja.demo.dto.AccountCreateRequest;
import com.pooja.demo.model.Account;
import com.pooja.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    
    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountCreateRequest request) {
        Account account = accountService.createAccount(request);
        return ResponseEntity.ok(account);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable String id) {
        Account account = accountService.getAccount(id);
        return ResponseEntity.ok(account);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
}
