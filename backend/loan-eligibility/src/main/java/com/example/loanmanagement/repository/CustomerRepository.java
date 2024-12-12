package com.example.loanmanagement.repository;

import com.example.loanmanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Custom query to find customer by first name and last name
    Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
