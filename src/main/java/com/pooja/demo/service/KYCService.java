package com.pooja.demo.service;

import com.pooja.demo.dto.AccountCreateRequest;
import com.pooja.demo.model.Account;
import com.pooja.demo.model.KYCDetails;
import com.pooja.demo.model.KYCStatus;
import com.pooja.demo.repository.KYCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KYCService {
    @Autowired
    private KYCRepository kycRepository;
    
    public KYCStatus performKYCCheck(Account account, AccountCreateRequest request) {
        // Validate Aadhar format
        if (!isValidAadharNumber(request.getAadharNumber())) {
            return saveKYC(account,request,KYCStatus.REJECTED);
        }
        
        // Validate PAN format
        if (!isValidPanNumber(request.getPanNumber())) {
            return saveKYC(account,request,KYCStatus.REJECTED);
        }

    
        return saveKYC(account,request,KYCStatus.VERIFIED);
    }

    private KYCStatus saveKYC(Account account, AccountCreateRequest request, KYCStatus status) {
        KYCDetails kyc = new KYCDetails();
        kyc.setAccount(account);
        kyc.setAadharNumber(request.getAadharNumber());
        kyc.setPanNumber(request.getPanNumber());
        kyc.setAddress(request.getAddress());
        kyc.setPhoneNumber(request.getPhoneNumber());
        kyc.setKYCstatus(status);
        kycRepository.save(kyc);
        return status;
    }

    private boolean isValidAadharNumber(String aadhar) {
        return aadhar != null && aadhar.matches("^[0-9]{12}$");
    }
    
    private boolean isValidPanNumber(String pan) {
        return pan != null && pan.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}");
    }
}
