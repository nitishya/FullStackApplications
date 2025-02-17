**Banking Application**

**Project Overview**
- This project aims to build a fully functional banking application. The application allows users to register, log in, deposit money, transfer funds, and view their transaction history. It includes features for authentication, authorization, and a dynamic dashboard to show account balances.

Features
1. User Registration and Login
   - Users can create accounts and log in securely.
   - Login uses JWT for authentication.
2. Dashboard
   - After login, users are redirected to their personal dashboard, showing account balance and transaction details.
3. Deposit Funds
   - Users can deposit money into their accounts.
4. Fund Transfer
   - Users can transfer money to other users.
   - Transfer operations are handled using multi-threading to ensure concurrent transaction handling.
5. Transaction History
   - View transaction history with complete details.
6. Logout
   - Logout functionality for user security.
     
**Technology Stack**
Backend:
     - Java 8
     - Spring Boot
     - Spring Security (JWT for Authentication)
     - Spring Data JPA (Hibernate)
     - Multi-threading for handling concurrent transactions
     
Frontend:
     - Angular 17
     - Bootstrap 5
     - Angular Material UI
     
Database:
     - MySQL

     
**Architecture**
- High-Level Diagram (HLD) of Banking Application
  
<img width="271" alt="allfunctions" src="https://github.com/user-attachments/assets/2b6a7cdf-edde-4758-b49a-dbb54c83ca0d" />

HLD of Spring Boot Application
<img width="440" alt="HLD of banking application " src="https://github.com/user-attachments/assets/616e04bb-15da-4a2c-bc5b-1a25aa06cabc" />

HLD of Angular Application

<img width="428" alt="angular-FrontEnd HLD" src="https://github.com/user-attachments/assets/bfdcafd2-0255-410a-b6d2-f871f70fb961" />

**Backend - Spring Boot Implementation**
Concepts Used:
- Java 8 Features: Utilized lambda expressions, streams, and functional interfaces.
- Collections: Used collections for storing user data and transaction details.
- Global Exception Handler: Implemented to manage errors and provide user-friendly error messages.
- Multi-threading: Enabled concurrent transactions to handle multiple users performing operations at the same time.
  
**Frontend - Angular Implementation**
Key Details:
- No standalone components used; components are organized into modules for better scalability and organization.
- Bootstrap is used for responsive design.
- Angular Material UI is installed to enhance the UI with modern components.

  
**Steps to Run the Application**
Backend:
- Clone the repository:
```
bash
Copy
git clone <repository-url>
```
Navigate to the backend folder and run:
```
bash
Copy
mvn spring-boot:run
```
The backend will be available at http://localhost:8080.

Frontend:
- Clone the repository:
```
bash
Copy
git clone <repository-url>
```
Navigate to the frontend folder and run:
```
bash
Copy
npm install
ng serve
```
The frontend will be available at http://localhost:4200.

Problems Faced and Solutions
1. Lombok Dependency Error
- Issue: Lombok was not recognized in Spring Tool Suite (STS).
- Solution: Install Lombok manually in STS.
2. Hibernate Issues with Spring 3.4.1
- Issue: Compatibility problems with Hibernate in Spring version 3.4.1.
- Solution: Downgraded STS version to 3.3.5 to resolve the issue.
3. Frontend: Transfer Amount Not Fetching toTransfer ID
- Issue: The toTransfer ID was not correctly fetched when transferring money.
- Solution: Fixed by correctly binding the toTransfer input field with Angular's two-way data binding.
4. Concurrency Issues in Transactions
- Issue: Errors while transferring and withdrawing funds due to lack of concurrency handling.
- Solution: Implemented multi-threading in the backend to handle multiple transactions simultaneously.
5. JWT Authentication Errors
- Issue: Errors while implementing JWT for authentication.
- Solution: Ensured compatibility between Spring Boot and the JWT library by using the correct versions of both libraries.
  
**Conclusion**
This banking application provides basic banking functionality, such as registration, login, fund deposit, transfers, and transaction history. With the implementation of Spring Boot and Angular, it ensures a robust backend with a responsive frontend. The application also handles concurrency to allow multiple transactions at the same time, ensuring the system’s reliability.

We will Create A banking Application in this Project - 

The application will look like this .
![image](https://github.com/user-attachments/assets/06dd2228-69bc-4eed-aa70-f819829182ca)

register page - 
![image](https://github.com/user-attachments/assets/d8c7d188-2d72-4039-a3cb-cd9fb03a96e6)

now we will login to the page - 
![image](https://github.com/user-attachments/assets/308a3ceb-4cc7-42e3-b8a7-3a995cf8ed8a)

Now we will navigated to the dashboard-
![image](https://github.com/user-attachments/assets/dc2c1fb6-d63f-4d8a-836a-4abc5b029fec)

after this we will deposit money to the account created - 
![image](https://github.com/user-attachments/assets/6add67ca-2fa6-4aa9-8b9b-90b179a37119)


After this we have transaction page to transfer any amount to other user-
![image](https://github.com/user-attachments/assets/a85a4f97-a4e6-4147-98a4-67cbe49d610f)

After transfering the amount we will be redirected to dashboard where we will see updated balance.
![image](https://github.com/user-attachments/assets/03ab3de9-3bcc-4422-8dfc-1b1c9e20d8b3)

We have login, Logout functionality to for authorisation and authentication. 

<img width="440" alt="HLD of banking application " src="https://github.com/user-attachments/assets/616e04bb-15da-4a2c-bc5b-1a25aa06cabc" />





So lets build it . Here is the HLD of the Springboot Application 

<img width="271" alt="allfunctions" src="https://github.com/user-attachments/assets/2b6a7cdf-edde-4758-b49a-dbb54c83ca0d" />







