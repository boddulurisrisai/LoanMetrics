import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-maxloan',
  templateUrl: './maxloan.component.html',
  styleUrls: ['./maxloan.component.css'],
})
export class MaxloanComponent {
  totalDebt: number = 0;
  annualIncome: number = 0;
  creditScore: number = 0;
  maxLoanAmount: number | null = null;
  errorMessage: string = '';

  private apiUrl = 'http://localhost:8080/api/loans/calculate-max-loan'; // API endpoint for max loan calculation

  constructor(private http: HttpClient) {}

  // Method to calculate the max loan amount
  onCalculateLoan(): void {
    const body = {
      totalDebt: this.totalDebt,
      annualIncome: this.annualIncome,
      creditScore: this.creditScore,
    };

    // Make the POST request to the backend API
    this.http.post<number>(this.apiUrl, body).subscribe({
      next: (result) => {
        this.maxLoanAmount = result;
        this.errorMessage = ''; // Clear any previous error message
      },
      error: (error) => {
        console.error('Error:', error);
        this.errorMessage = 'Failed to calculate loan amount. Please try again.';
        this.maxLoanAmount = null; // Reset the loan amount
      },
    });
  }
}
