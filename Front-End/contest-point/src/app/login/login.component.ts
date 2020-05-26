import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ErrorStateMatcher } from '@angular/material';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { User } from '../_models/user';
import { AuthService } from '../_services/auth.service';
import { Router } from '@angular/router';
import { NotificationService } from '../_services/notification.service';
import { JwtResponse } from '../_models/jwtResponse';

export class LoginErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  @Output() cancelLogin = new EventEmitter();
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  passwordFormControl = new FormControl('', [
    Validators.required,
  ]);

  matcher = new LoginErrorStateMatcher();

  hide = true;

  user: User = new User();

  constructor(public authService: AuthService, private router: Router, private notificationService: NotificationService) { }

  ngOnInit() {
  }

  cancel() {
    this.cancelLogin.emit(false);
  }

  login() {
    this.authService.login(this.user).subscribe(
      (data: JwtResponse) => {
        localStorage.setItem('token', "Bearer " + data.token);
        this.notificationService.success("Logged in!");
        this.router.navigate(['/dashboard']);
      },
      error => {
        this.notificationService.error("Logging in did not work!");
      }
    );
  }

}
