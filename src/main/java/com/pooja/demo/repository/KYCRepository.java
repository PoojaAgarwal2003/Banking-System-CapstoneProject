package com.pooja.demo.repository;

import com.pooja.demo.model.KYCDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KYCRepository extends JpaRepository<KYCDetails, String> {
    KYCDetails findByAccountId(String accountId);
}
