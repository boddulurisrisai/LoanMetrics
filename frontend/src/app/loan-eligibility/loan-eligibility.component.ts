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
  age?: number;
  annualIncome?: number;
  debts?: number;
  creditScore?: number;
  employmentStatus: string = ''; // Updated to handle radio button values
  eligibilityResult?: string;

  // Inject HttpClient
  constructor(private http: HttpClient) {}

  // Submit form
  onSubmit(): void {
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
  }
}
