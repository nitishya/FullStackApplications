import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLoggedIn: boolean = false;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.checkLoginStatus();
  }

  checkLoginStatus() {
    this.isLoggedIn = !!localStorage.getItem('userId'); // ✅ Check if userId exists

    // ✅ Watch localStorage changes to update the header dynamically
    window.addEventListener('storage', () => {
      this.isLoggedIn = !!localStorage.getItem('userId');
    });
  }

  logout() {
    localStorage.clear();
    this.isLoggedIn = false;
    this.router.navigate(['/login']);
  }
}
