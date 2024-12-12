import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoanEligibilityComponent } from './loan-eligibility/loan-eligibility.component';

const routes: Routes = [
  { path: '', component: LoanEligibilityComponent }, // Default route
  { path: 'loan-eligibility', component: LoanEligibilityComponent } // Explicit route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
