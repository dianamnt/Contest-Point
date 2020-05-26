import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material';
import { User } from '../_models/user';
import { AuthService } from '../_services/auth.service';
import { Router } from '@angular/router';
import { NotificationService } from '../_services/notification.service';

export class RegisterErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  @Output() cancelRegister = new EventEmitter();
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  passwordFormControl = new FormControl('', [
    Validators.required,
  ]);

  hide = true;

  user: User = new User();

  matcher = new RegisterErrorStateMatcher();

  constructor(public authSerice: AuthService, private router: Router, private notificationService: NotificationService) { }

  ngOnInit() {
  }

  cancel() {
    this.cancelRegister.emit(false);
  }

  register() {
    if (this.user.institutionName == null) {
      this.user.institutionName = "";
    }
    this.user.profilePicture = "";
    this.authSerice.register(this.user).subscribe(
      (data: User) => {
        this.notificationService.success("Registered!")
        var frm = document.forms['registerForm'];
        frm.reset();
        this.cancel();
      },
      error => {
        this.notificationService.error("Registering did not work!");
      }
    );
  }

}
