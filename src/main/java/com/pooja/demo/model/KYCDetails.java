package com.pooja.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "kyc_details")
public class KYCDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
    
    @Column(nullable = false)
    private String aadharNumber;
    
    @Column(nullable = false)
    private String panNumber;
    
    private String address;
    
    @Column(nullable = false)
    private String phoneNumber;
    
    @Enumerated(EnumType.STRING)
    private KYCStatus KYCstatus = KYCStatus.PENDING;
}


