import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private apiUrl = 'http://localhost:8080/api/accounts';  // ✅ Backend API Base URL

  constructor(private http: HttpClient) {}

  // ✅ Fetch all accounts for the logged-in user
  getUserAccounts(userId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/user/${userId}`);
  }
  
  

  // ✅ Create a new bank account for the user
  createAccount(): Observable<any> {
    const userId = localStorage.getItem('userId'); // Get user ID
    return this.http.post(`${this.apiUrl}/create/${userId}`, {});
  }

  // ✅ Deposit money into a specific account
  deposit(accountId: number, amount: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/${accountId}/deposit`, { amount });
  }

  // ✅ Withdraw money from a specific account
  withdraw(accountId: number, amount: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/${accountId}/withdraw`, { amount });
  }
}

