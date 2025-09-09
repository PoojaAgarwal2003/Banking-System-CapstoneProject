package com.pooja.demo.service;

import com.pooja.demo.model.Account;
import com.pooja.demo.dto.AccountCreateRequest;
import com.pooja.demo.model.AccountStatus;
import com.pooja.demo.repository.AccountRepository;
import com.pooja.demo.service.FraudCheckService;
import com.pooja.demo.service.KYCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private KYCService kycService;
    
    @Autowired
    private FraudCheckService fraudCheckService;
    
    @Transactional
    public Account createAccount(AccountCreateRequest request) {
        // Create new account
        Account account = new Account();
        account.setUserId(request.getUserId());
        account.setAccountNumber(generateAccountNumber());
        account.setStatus(AccountStatus.PENDING);
        
        // Save initial account
        account = accountRepository.save(account);
        
        // Perform KYC check
        boolean kycValid = kycService.performKYCCheck(account.getId(), request);
        
        // Perform fraud check
        boolean fraudCheckPassed = fraudCheckService.checkFraud(request.getAadharNumber());
        
        // Update account status based on checks
        if (kycValid && fraudCheckPassed) {
            account.setStatus(AccountStatus.APPROVED);
        } else {
            account.setStatus(AccountStatus.REJECTED);
        }
        
        return accountRepository.save(account);
    }
    
    public Account getAccount(String id) {
        return accountRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Account not found"));
    }
    
    public void deleteAccount(String id) {
        Account account = getAccount(id);
        account.setStatus(AccountStatus.CLOSED);
        accountRepository.save(account);
    }
    
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    
    private String generateAccountNumber() {
        // Generate a unique 12-digit account number
        return String.format("%012d", UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
    }
}
