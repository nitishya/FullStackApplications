import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  private apiUrl = 'http://localhost:8080/api/transactions';  // Backend API Base URL

  constructor(private http: HttpClient) {}

  // Fetch all transactions for a specific account
  getTransactions(accountId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${accountId}`);
  }

  // Transfer money from one account to another
  transfer(fromAccountId: number, toAccountId: number, amount: number): Observable<any> {
    const requestBody = { amount };  // Modify as needed based on backend expectations
    return this.http.post(`${this.apiUrl}/${fromAccountId}/transfer/${toAccountId}`, requestBody);
  }
}
