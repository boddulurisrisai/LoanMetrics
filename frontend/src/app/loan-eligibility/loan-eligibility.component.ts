import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http'; // Import HttpClientModule

@Component({
  selector: 'app-loan-eligibility',
  standalone: true,
  templateUrl: './loan-eligibility.component.html',
  styleUrls: ['./loan-eligibility.component.css'],
  imports: [FormsModule, CommonModule, HttpClientModule], // Add HttpClientModule here
})
export class LoanEligibilityComponent {
  // Form data
  firstName: string = '';
  lastName: string = '';
  age?: number = 0;
  annualIncome?: number = 0;
  debts?: number = 0;
  creditScore?: number = 0;
  employmentStatus: string = ''; // Updated to handle radio button values
  eligibilityResult?: string;

  // Inject HttpClient
  constructor(private http: HttpClient) {}

  // Submit form
  onSubmit(): void {
    // Validate the form data before submitting
    if (this.isFormValid()) {
      // Prepare the customer data
      const customerData = {
        firstName: this.firstName,
        lastName: this.lastName,
        age: this.age,
        annualIncome: this.annualIncome,
        creditScore: this.creditScore,
        existingDebts: this.debts,
        employmentStatus: this.employmentStatus, // Use the selected radio button value
      };

      console.log('Sending customer data:', customerData);

      // Send POST request to backend
      this.http
        .post('http://localhost:8080/api/loans/check-customer', customerData, { responseType: 'text' })
        .subscribe(
          (response: string) => {
            console.log('Response received:', response);
            this.eligibilityResult = response; // Display plain text response
          },
          (error: HttpErrorResponse) => {
            console.error('Error occurred:', error);
            // Check if error contains a text message
            if (typeof error.error === 'string') {
              this.eligibilityResult = error.error; // Display the error message from the server
            } else {
              this.eligibilityResult = 'An unexpected error occurred. Please try again later.';
            }
          }
        );
    } else {
      this.eligibilityResult = 'Please correct the form before submitting.';
    }
  }

  // Form validation logic
  private isFormValid(): boolean {
    // Check if required fields are filled and validate values
    if (!this.firstName || !this.lastName || !this.age || !this.annualIncome || !this.debts || !this.creditScore || !this.employmentStatus) {
      return false; // Return false if any required field is missing
    }

    // Validate credit score range (300 - 850)
    if (this.creditScore < 300 || this.creditScore > 850) {
      this.eligibilityResult = 'Credit score must be between 300 and 850.';
      return false;
    }

    // Validate annual income and debts as positive numbers
    if (this.annualIncome <= 0) {
      this.eligibilityResult = 'Annual income must be a positive number.';
      return false;
    }

    if (this.debts <= 0) {
      this.eligibilityResult = 'Total debts must be a positive number.';
      return false;
    }

    return true; // Return true if all validations pass
  }
}
