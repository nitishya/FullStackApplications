import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginData = { username: '', password: '' };
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    this.authService.login(this.loginData).subscribe(
      (response) => {
        console.log('Login Response:', response); // ✅ Debug log
  
        if (response.userId) {  // ✅ Ensure userId exists in response
          localStorage.setItem('userId', response.userId.toString()); // ✅ Store as a string
        } else {
          console.error('Error: userId is missing in response');
        }
  
        localStorage.setItem('token', response.token);
        localStorage.setItem('username', response.username);
        this.router.navigate(['/dashboard']);
      },
      (error) => {
        this.errorMessage = "Invalid username or password";
      }
    );
  }
  
}
