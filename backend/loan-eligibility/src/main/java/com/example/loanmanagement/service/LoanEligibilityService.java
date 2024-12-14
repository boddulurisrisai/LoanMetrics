package com.example.loanmanagement.service;

import com.example.loanmanagement.entity.Customer;
import com.example.loanmanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoanEligibilityService {

    private final CustomerRepository customerRepository;

    @Autowired
    public LoanEligibilityService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Method to fetch customer by first name and last name
    public Optional<Customer> getCustomerByName(String firstName, String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    // Method to update the credit score using custom query
    @Transactional
    public void updateCustomerCreditScore(String firstName, String lastName, int newCreditScore) {
        customerRepository.updateCreditScoreByName(firstName, lastName, newCreditScore);
    }

    // Loan eligibility check logic
    public boolean checkLoanEligibility(Customer customer) {
        // Loan eligibility criteria
        if (customer.getCreditScore() < 650) {
            return false;
        }
        if (customer.getAnnualIncome() < 30000) {
            return false;
        }
        if (customer.getExistingDebts() / customer.getAnnualIncome() > 0.40) {
            return false;
        }
        if (customer.getAge() < 21) {
            return false;
        }
        if ("Unemployed".equalsIgnoreCase(customer.getEmploymentStatus())) {
            return false;
        }

        // If all criteria are satisfied
        return true;
    }
}
