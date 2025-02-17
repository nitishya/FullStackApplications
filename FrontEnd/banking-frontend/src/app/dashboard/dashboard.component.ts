import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../services/account.service';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  username: string = 'User';
  userId: string | null = null;
  isLoggedIn: boolean = false;
  accounts: any[] = [];
  transactions: any[] = [];

  constructor(
    private accountService: AccountService,
    private transactionService: TransactionService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.username = localStorage.getItem('username') || 'User'; // ✅ Get stored username
    this.userId = localStorage.getItem('userId'); // ✅ Get stored userId
    this.isLoggedIn = !!this.userId; // ✅ Check if user is logged in

    if (this.isLoggedIn) {
      this.loadAccounts();
    }
  }

  loadAccounts() {
    if (!this.userId) return;

    const userId = Number(this.userId);
    this.accountService.getUserAccounts(userId).subscribe(
      (response) => {
        this.accounts = response;
        if (this.accounts.length > 0) {
          this.loadTransactions(this.accounts[0].id); // ✅ Load transactions for the first account
        }
      },
      (error) => {
        console.error('Error loading accounts:', error);
      }
    );
  }

  loadTransactions(accountId: number) {
    this.transactionService.getTransactions(accountId).subscribe(
      (response) => {
        this.transactions = response;
      },
      (error) => {
        console.error('Error loading transactions:', error);
      }
    );
  }
}
