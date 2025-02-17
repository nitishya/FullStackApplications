import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  accounts: any[] = [];
  message: string = '';
  isLoggedIn: boolean = false;

  constructor(private accountService: AccountService, private router: Router) {}

  ngOnInit(): void {
    this.checkLoginStatus();
  }

  checkLoginStatus() {
    const userIdStr: string | null = localStorage.getItem('userId');
    
    // Check if userId exists in localStorage to verify login status
    if (userIdStr && userIdStr !== 'undefined' && !isNaN(Number(userIdStr))) {
      this.isLoggedIn = true;
      this.loadAccounts();  // Load accounts if user is logged in
    } else {
      this.isLoggedIn = false;
      this.message = "⚠️ You need to log in first!";
    }
  }

  loadAccounts() {
    if (!this.isLoggedIn) return;

    const userId: number = Number(localStorage.getItem('userId'));

    this.accountService.getUserAccounts(userId).subscribe(
      (response) => {
        this.accounts = response;
      },
      (error) => {
        console.error('Error loading accounts:', error);
      }
    );
  }

  createAccount() {
    if (!this.isLoggedIn) {
      this.message = "⚠️ You need to log in first!";
      return;
    }

    this.accountService.createAccount().subscribe(
      (response) => {
        this.message = "New bank account created successfully!";
        this.loadAccounts();  // Refresh account list
      },
      (error) => {
        console.error('Error creating account:', error);
      }
    );
  }

  deposit(accountId: number) {
    if (!this.isLoggedIn) {
      this.message = "⚠️ You need to log in first!";
      return;
    }

    const amount = prompt("Enter deposit amount:");
    if (amount) {
      this.accountService.deposit(accountId, parseFloat(amount)).subscribe(
        () => {
          this.message = "Deposit successful!";
          this.loadAccounts();  // Refresh account list
        },
        (error) => {
          console.error('Error depositing money:', error);
        }
      );
    }
  }

  withdraw(accountId: number) {
    if (!this.isLoggedIn) {
      this.message = "⚠️ You need to log in first!";
      return;
    }

    const amount = prompt("Enter withdrawal amount:");
    if (amount) {
      this.accountService.withdraw(accountId, parseFloat(amount)).subscribe(
        () => {
          this.message = "Withdrawal successful!";
          this.loadAccounts();  // Refresh account list
        },
        (error) => {
          console.error('Error withdrawing money:', error);
        }
      );
    }
  }
}
