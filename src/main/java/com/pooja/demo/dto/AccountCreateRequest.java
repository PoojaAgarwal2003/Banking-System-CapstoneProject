package com.pooja.demo.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class AccountCreateRequest {
    @NotBlank(message = "User ID is required")
    private String userId;
    
    @NotBlank(message = "Aadhar number is required")
    private String aadharNumber;
    
    @NotBlank(message = "PAN number is required")
    private String panNumber;
    
    @NotBlank(message = "Address is required")
    private String address;
    
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
}
