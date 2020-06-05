import { Component, OnInit } from '@angular/core';
import { ContestService } from '../_services/contest.service';
import { ContestDetailed } from '../_models/contestdetailed';
import { Aux } from '../_models/aux';
import { NotificationService } from '../_services/notification.service';
import { User } from '../_models/user';
import { AuthService } from '../_services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dialog-delete',
  templateUrl: './dialog-delete.component.html',
  styleUrls: ['./dialog-delete.component.scss']
})
export class DialogDeleteComponent implements OnInit {
  contestId: number;
  contestName: string;
  currentUser: User;

  constructor(private router: Router, private notificationService: NotificationService, private contestService: ContestService, private authService: AuthService) { }

  ngOnInit(): void {
    this.contestId = parseInt(localStorage.getItem('currentContest'));
    this.currentUser = this.authService.getCurrentUser();
    this.contestService.findByIdDetailed(this.contestId).subscribe(
      (data: ContestDetailed) => {
        this.contestName = data.contestName;
      }
    );
  }

  submit() {
    this.contestService.deleteContest(this.currentUser.userId, this.contestId).subscribe(
      (data: Aux) => {
        this.router.navigate(['/dashboard']);
        this.notificationService.success("You deleted " + this.contestName);
      },
      error => {
        this.notificationService.error(this.contestName + "could not be deleted");
      }
    );
  }

}
