import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service'
import { UserService } from '../_services/user.service'
import { User } from '../_models/user';
import { Validators, FormControl } from '@angular/forms';
import { RegisterErrorStateMatcher } from '../register/register.component';
import { NotificationService } from '../_services/notification.service';
import { JwtResponse } from '../_models/jwtResponse'

@Component({
  selector: 'app-dialog-edit-profile',
  templateUrl: './dialog-edit-profile.component.html',
  styleUrls: ['./dialog-edit-profile.component.scss']
})
export class DialogEditProfileComponent implements OnInit {
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  passwordFormControl = new FormControl('', [
  ]);

  hide = true;

  user: User = new User();

  matcher = new RegisterErrorStateMatcher();

  constructor(private authService: AuthService, private userService: UserService, private notifService: NotificationService) { }

  ngOnInit(): void {
    this.user = this.authService.getCurrentUser();
  }

  submit() {
    if (this.user.password == null) {
      this.user.password = "";
    }
    if (this.user.institutionName == null) {
      this.user.institutionName = "";
    }
    this.user.profilePicture = "";
    this.userService.updateUser(this.user).subscribe(
      (data: User) => {
        this.notifService.success("Profile updated!");
      },
      error => {
        this.notifService.error("Updating did not work!");
      }
    );
  }

}
