package com.pooja.demo.service;

import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

@Service
public class FraudCheckService {
    private static final String FRAUD_LIST_PATH = "fraud/blacklist.txt";
    
    public boolean checkFraud(String aadharNumber) {
        try {
            Path fraudListPath = Paths.get(FRAUD_LIST_PATH);
            if (Files.exists(fraudListPath)) {
                return !Files.lines(fraudListPath)
                    .anyMatch(line -> line.trim().equals(aadharNumber));
            }
            // If fraud list doesn't exist, assume no fraud
            return true;
        } catch (IOException e) {
            // Log error and assume no fraud in case of error
            e.printStackTrace();
            return true;
        }
    }
}
