package com.example.loanmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private double annualIncome; // This represents the customer's annual income
    private int creditScore;
    private double existingDebts;
    private String employmentStatus;

    private double existingLoans;
    private double totalDebt;

    // Default constructor
    public Customer() {
    }

    // Constructor to initialize the fields
    public Customer(String firstName, String lastName, String email, int age, double annualIncome,
                    int creditScore, double existingDebts, String employmentStatus, double existingLoans, double totalDebt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.annualIncome = annualIncome;
        this.creditScore = creditScore;
        this.existingDebts = existingDebts;
        this.employmentStatus = employmentStatus;
        this.existingLoans = existingLoans;
        this.totalDebt = totalDebt;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public double getExistingDebts() {
        return existingDebts;
    }

    public void setExistingDebts(double existingDebts) {
        this.existingDebts = existingDebts;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public double getExistingLoans() {
        return existingLoans;
    }

    public void setExistingLoans(double existingLoans) {
        this.existingLoans = existingLoans;
    }

    public double getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(double totalDebt) {
        this.totalDebt = totalDebt;
    }



    // Optional: Override toString() method for debugging or logging purposes
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", annualIncome=" + annualIncome +
                ", creditScore=" + creditScore +
                ", existingDebts=" + existingDebts +
                ", employmentStatus='" + employmentStatus + '\'' +
                ", existingLoans=" + existingLoans +
                ", totalDebt=" + totalDebt +
                '}';
    }
}
