import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../_services/notification.service';
import { ContestDetailed } from '../_models/contestdetailed';
import { ContestService } from '../_services/contest.service';
import { Router } from '@angular/router';
import { ContractDetailed } from '../_models/contractdetailed';
import { ContractService } from '../_services/contract.service';
import { User } from '../_models/user';
import { AuthService } from '../_services/auth.service'
import { Contest } from '../_models/contest';
import { MatDialog } from '@angular/material/dialog';
import { DialogEnrollComponent } from '../dialog-enroll/dialog-enroll.component'
import { DialogContestantsComponent } from '../dialog-contestants/dialog-contestants.component'

@Component({
  selector: 'app-contest',
  templateUrl: './contest.component.html',
  styleUrls: ['./contest.component.scss']
})
export class ContestComponent implements OnInit {
  contest: ContestDetailed;
  contestId: number;
  currentUser: User;
  userDisplayed: string = "";

  constructor(private notificationService: NotificationService, private contractService: ContractService, private router: Router, private contestService: ContestService, private authService: AuthService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.contestId = parseInt(localStorage.getItem('currentContest'));
    this.currentUser = this.authService.getCurrentUser();
    this.contestService.findByIdDetailed(this.contestId).subscribe(
      (data: ContestDetailed) => {
        this.contest = data;
        console.log(this.contest);
        if (this.currentUser.userId == this.contest.userId) {
          (<HTMLElement>document.querySelector('#enrollButton')).style.display = 'none';
          (<HTMLElement>document.querySelector('#contestantsButton')).style.display = 'inline';
          this.userDisplayed = " you"
        }
      },
      error => {
        this.notificationService.error("Contest details were not received");
      }
    );
  }

  like() {
    (<HTMLElement>document.querySelector('#like')).style.display = 'none';
    (<HTMLElement>document.querySelector('#dislike')).style.display = 'inline';
  }

  dislike() {
    (<HTMLElement>document.querySelector('#dislike')).style.display = 'none';
    (<HTMLElement>document.querySelector('#like')).style.display = 'inline';
  }

  openDialog() {
    const dialogRef = this.dialog.open(DialogEnrollComponent);

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  openDialogContestants() {
    const dialogRef = this.dialog.open(DialogContestantsComponent);

    dialogRef.afterClosed().subscribe(result => {
    });
  }

}
