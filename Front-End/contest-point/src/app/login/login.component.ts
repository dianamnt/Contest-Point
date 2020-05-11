import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ErrorStateMatcher } from '@angular/material';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { User } from '../_models/user';
import { AuthService } from '../_services/auth.service';
import { Router } from '@angular/router';

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

  constructor(public authSerice: AuthService, private router: Router) { }

  ngOnInit() {
  }

  cancel() {
    this.cancelLogin.emit(false);
  }

  login() {
    this.authSerice.login(this.user).subscribe((data: User) => {
      if (data != null) {
        this.router.navigate(['/dashboard']);
        console.log(data);
      } else {
        console.log('naspa nu o mers scz');
      }
    });
  }

}
