import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../_models/user';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {

  user: User = new User();
  initials: String = "";

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.user = this.authService.getCurrentUser();
    this.initials = this.user.firstName[0].toUpperCase() + this.user.lastName[0].toUpperCase();
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/welcome']);
  }

  goToAddContest() {
    this.router.navigate(['/add']);
  }

  goToDashboard() {
    this.router.navigate(['/dashboard']);
  }

  goToMyContests() {
    this.router.navigate(['/my-contests']);
  }

  goToMyFavorites() {
    this.router.navigate(['/favorites']);
  }

}
