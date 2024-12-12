import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { importProvidersFrom } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { LoanEligibilityComponent } from './app/loan-eligibility/loan-eligibility.component';

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter([
      { path: '', component: LoanEligibilityComponent }, // Default route
      { path: 'loan-eligibility', component: LoanEligibilityComponent }
    ]),
    importProvidersFrom(BrowserModule, FormsModule) // Import necessary modules
  ]
}).catch(err => console.error(err));
