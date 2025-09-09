package com.pooja.demo.service;

import com.pooja.demo.dto.AccountCreateRequest;
import com.pooja.demo.model.KYCDetails;
import com.pooja.demo.repository.KYCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KYCService {
    @Autowired
    private KYCRepository kycRepository;
    
    public boolean performKYCCheck(String accountId, AccountCreateRequest request) {
        // Validate Aadhar format
        if (!isValidAadharNumber(request.getAadharNumber())) {
            return false;
        }
        
        // Validate PAN format
        if (!isValidPanNumber(request.getPanNumber())) {
            return false;
        }
        
        // Save KYC details
        KYCDetails kyc = new KYCDetails();
        kyc.setAadharNumber(request.getAadharNumber());
        kyc.setPanNumber(request.getPanNumber());
        kyc.setAddress(request.getAddress());
        kyc.setPhoneNumber(request.getPhoneNumber());
        kycRepository.save(kyc);
        
        return true;
    }
    
    private boolean isValidAadharNumber(String aadhar) {
        return aadhar != null && aadhar.matches("^[0-9]{12}$");
    }
    
    private boolean isValidPanNumber(String pan) {
        return pan != null && pan.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}");
    }
}
