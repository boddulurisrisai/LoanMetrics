package com.example.loanmanagement.controller;

import com.example.loanmanagement.entity.Customer;
import com.example.loanmanagement.service.LoanEligibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "http://localhost:4200") // Allow CORS for this controller from frontend
public class LoanEligibilityController {

    private final LoanEligibilityService loanEligibilityService;

    @Autowired
    public LoanEligibilityController(LoanEligibilityService loanEligibilityService) {
        this.loanEligibilityService = loanEligibilityService;
    }

    // Endpoint to check loan eligibility via POST request
    @PostMapping("/check-customer")
    public ResponseEntity<String> checkLoanEligibility(@RequestBody Customer customerRequest) {
        try {
            // Fetch customer from the database using firstName and lastName
            Optional<Customer> existingCustomer = loanEligibilityService.getCustomerByName(
                    customerRequest.getFirstName(),
                    customerRequest.getLastName()
            );

            if (existingCustomer.isPresent()) {
                // Update credit score
                loanEligibilityService.updateCustomerCreditScore(
                        customerRequest.getFirstName(),
                        customerRequest.getLastName(),
                        customerRequest.getCreditScore()
                );

                // Use the updated details to perform the eligibility check
                boolean isEligible = loanEligibilityService.checkLoanEligibility(customerRequest);
                return ResponseEntity.ok(
                        isEligible ? "Customer is eligible for the loan." : "Customer is not eligible for the loan."
                );
            } else {
                // Customer not found in the database
                return ResponseEntity.status(404).body("Customer is not found in the database.");
            }
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();  // Or use a logger like LoggerFactory.getLogger(LoanEligibilityController.class).error(e.getMessage(), e);
            return ResponseEntity.status(500).body("Error occurred while checking eligibility.");
        }
    }
}
