package com.example.loanmanagement.service;

import com.example.loanmanagement.entity.Customer;
import com.example.loanmanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import java.util.Optional;

@Service
public class LoanEligibilityService {
    private static final Logger logger = LogManager.getLogger(LoanEligibilityService.class);  // Logger initialization

    @Autowired
    private CustomerRepository customerRepository;
    // Method to fetch customer by name (this should fetch from your database)
    public Optional<Customer> getCustomerByName(String firstName, String lastName) {
        logger.info("Searching for customer with first name: " + firstName + " and last name: " + lastName);
        // Retrieve customer from the database
        Optional<Customer> customer = customerRepository.findByFirstNameAndLastName(firstName, lastName);
        if (customer.isPresent()) {
            logger.info("Customer found: " + customer.get());
        } else {
            logger.warn("No customer found with the given name.");
        }
        return customer;
    }

    // Method to update customer credit score (this should update the database)
    public void updateCustomerCreditScore(String firstName, String lastName, double creditScore) {
        // Implement logic to update the credit score of the customer in the database
    }

    // Method to check loan eligibility
    public String checkLoanEligibility(Customer customer) {
        // Check if customer is above 18 years old
        if (customer.getAge() <= 18) {
            return "Rejected: Customer's age is below 18.";
        }

        // Check if the credit score is below 650
        if (customer.getCreditScore() < 650) {
            return "Rejected: Customer's credit score is below 650.";
        }

        // Check if the total outstanding loan exceeds $10,000
        if (customer.getExistingLoans() > 10000) {
            return "Rejected: Customer has existing loans exceeding $10,000.";
        }

        // Calculate the Income-to-Debt Ratio (IDR)
        double idr = customer.getTotalDebt() / customer.getAnnualIncome();

        // Check if IDR is below 40%
        if (idr >= 0.40) {
            return "Rejected: Customer's Income-to-Debt Ratio exceeds 40%.";
        }

        // If all conditions pass, the customer is eligible
        return "Approved: Customer is eligible for the loan.";
    }
}