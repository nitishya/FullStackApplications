import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerData = { username: '', email: '', password: '' };
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onRegister() {
    this.authService.register(this.registerData).subscribe(
      (response) => {
        this.successMessage = "Registration successful! Redirecting to login...";
        setTimeout(() => {
          this.router.navigate(['/login']); // ✅ Redirect after 3 seconds
        }, 3000);
      },
      (error) => {
        this.errorMessage = "Error registering user. Try again.";
      }
    );
  }
}
