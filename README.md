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



Now we will start with Backend which is in java using the springboot framework.
- We are using all java 8 concept in this.
- We are using collections in this.
- We are using Global Exceptional Handler in this project.
- We are using multi-threading for multiple transactions happening simultaneously.

So lets build it . Here is the HLD of the Springboot Application 

<img width="271" alt="allfunctions" src="https://github.com/user-attachments/assets/2b6a7cdf-edde-4758-b49a-dbb54c83ca0d" />



Okay now we will build Front-End which is Angular 17. 
- We will use no standalone components for it.
- Bootstrap is installed.
- Angular Material UI is installed according to the angular version.

So lets build it . Here is the HLD of the Angular Application.


<img width="428" alt="angular-FrontEnd HLD" src="https://github.com/user-attachments/assets/bfdcafd2-0255-410a-b6d2-f871f70fb961" />


-Problems faced-
- Lombok dependency error - You need to install it manually to your STS
- Hibernate for Spring version 3.4.1 have some issues. You can solve it by changing STS version 3.3.5
- In frontEnd while transfering amount the toTransfer Id is not getting fetched.
- While transfering and withdrawing amount due to lack of concurrency getting error. Solved using the multithreading.
- While implementing JWT got error. Try to be specific with the version of Springboot you are using and what version jjwt you are using this will decide all the other files and solving bugs regarding it.
