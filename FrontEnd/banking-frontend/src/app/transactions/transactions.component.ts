import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../services/account.service';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {
  accounts: any[] = [];
  transactions: any[] = [];
  selectedAccountId: number | null = null;
  toAccountId: number | null = null;
  transferAmount: number | null = null;
  isLoggedIn: boolean = false;

  constructor(
    private accountService: AccountService,
    private transactionService: TransactionService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.checkLoginStatus();
    if (this.isLoggedIn) {
      this.loadAccounts();
    }
  }

  checkLoginStatus() {
    const userIdStr = localStorage.getItem('userId');
    if (!userIdStr || userIdStr === 'undefined' || isNaN(Number(userIdStr))) {
      this.isLoggedIn = false;
    } else {
      this.isLoggedIn = true;
    }
  }

  loadAccounts() {
    if (!this.isLoggedIn) return;

    const userId = Number(localStorage.getItem('userId'));
    this.accountService.getUserAccounts(userId).subscribe(
      (response) => {
        this.accounts = response;
        if (this.accounts.length > 0) {
          this.selectedAccountId = this.accounts[0].id;
          this.loadTransactions();
        }
      },
      (error) => {
        console.error('Error loading accounts:', error);
      }
    );
  }

  loadTransactions() {
    if (!this.selectedAccountId) return;

    this.transactionService.getTransactions(this.selectedAccountId).subscribe(
      (response) => {
        this.transactions = response;
      },
      (error) => {
        console.error('Error loading transactions:', error);
      }
    );
  }

  transferMoney() {
    if (this.selectedAccountId && this.toAccountId && this.transferAmount) {
        this.transactionService.transfer(this.selectedAccountId, this.toAccountId, this.transferAmount).subscribe(
            (response) => {
                console.log("Transaction successful!", response);
                // After successful transaction, refresh the transaction list
                this.loadTransactions();
                setTimeout(() => {
                    this.router.navigate(['/accounts']);
                }, 1000);
            },
            (error) => {
                console.error("Error processing transaction", error);
            }
        );
    } else {
        console.error("Please fill in all transfer details");
    }
}
}
