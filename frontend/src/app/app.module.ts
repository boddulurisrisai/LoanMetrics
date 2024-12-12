import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // Import FormsModule
import { CommonModule } from '@angular/common';
 // Import CommonModule
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoanEligibilityComponent } from './loan-eligibility/loan-eligibility.component';

@NgModule({
  declarations: [
    AppComponent,
    LoanEligibilityComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,  // Add FormsModule to imports
    CommonModule,
    HttpClientModule  // Add CommonModule to imports
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
