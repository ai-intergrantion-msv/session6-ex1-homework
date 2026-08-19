package com.rikkeipay.demo;

import com.rikkeipay.dto.TransferRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

public class ValidationDemo {
    public static void main(String[] args) {
        System.out.println("--- Starting TransferRequest Validation Demo ---");
        
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();

            // 1. Invalid Request (Missing receiver, invalid bank code, amount too low)
            System.out.println("\n[1] Testing Invalid Request...");
            TransferRequest invalidRequest = new TransferRequest(
                "SENDER_123",
                "", // Blank receiver
                "INVALID_BANK", // Not in valid list
                new BigDecimal("5000"), // < 10000
                "Test transfer"
            );

            Set<ConstraintViolation<TransferRequest>> violations = validator.validate(invalidRequest);
            
            if (!violations.isEmpty()) {
                System.out.println("Validation failed! Errors found:");
                for (ConstraintViolation<TransferRequest> violation : violations) {
                    System.out.println("- " + violation.getPropertyPath() + ": " + violation.getMessage());
                }
            } else {
                System.out.println("Request is valid!");
            }

            // 2. Valid Request
            System.out.println("\n[2] Testing Valid Request...");
            TransferRequest validRequest = new TransferRequest(
                "SENDER_123",
                "0987654321",
                "VCB",
                new BigDecimal("150000"),
                "Payment for order"
            );

            Set<ConstraintViolation<TransferRequest>> validViolations = validator.validate(validRequest);
            
            if (!validViolations.isEmpty()) {
                System.out.println("Validation failed! Errors found:");
            } else {
                System.out.println("Request is valid!");
            }
        }
    }
}
