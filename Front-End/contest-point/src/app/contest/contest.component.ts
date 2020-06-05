import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../_services/notification.service';
import { ContestDetailed } from '../_models/contestdetailed';
import { ContestService } from '../_services/contest.service';
import { Router } from '@angular/router';
import { ContractDetailed } from '../_models/contractdetailed';
import { ContractService } from '../_services/contract.service';
import { User } from '../_models/user';
import { AuthService } from '../_services/auth.service'
import { LikeService } from '../_services/like.service'
import { Contest } from '../_models/contest';
import { MatDialog } from '@angular/material/dialog';
import { DialogEnrollComponent } from '../dialog-enroll/dialog-enroll.component'
import { DialogContestantsComponent } from '../dialog-contestants/dialog-contestants.component'
import { DialogUnenrollComponent } from '../dialog-unenroll/dialog-unenroll.component'
import { DialogDeleteComponent } from '../dialog-delete/dialog-delete.component'
import { ParticipationContract } from '../_models/participationcontract';
import { Like } from '../_models/like';
import { Aux } from '../_models/auxiliary';

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
  currentLike: Like = new Like();
  noLikes: number;

  constructor(private notificationService: NotificationService, private contractService: ContractService, private router: Router, private contestService: ContestService, private authService: AuthService, public dialog: MatDialog, private likeService: LikeService) { }

  ngOnInit(): void {
    this.contestId = parseInt(localStorage.getItem('currentContest'));
    this.currentUser = this.authService.getCurrentUser();
    this.contestService.findByIdDetailed(this.contestId).subscribe(
      (data: ContestDetailed) => {
        this.contest = data;
        this.noLikes = this.contest.likes.length;
        if (this.currentUser.userId == this.contest.userId) {
          (<HTMLElement>document.querySelector('#contestantsButton')).style.display = 'inline';
          this.userDisplayed = " you";
          (<HTMLElement>document.querySelector('#delete')).style.display = 'inline';
        }
        else {
          this.contractService.isEnrolled(this.currentUser.userId, this.contest.contestId).subscribe(
            (data: ParticipationContract) => {
              (<HTMLElement>document.querySelector('#unenrollButton')).style.display = 'inline';
            },
            error => {
              (<HTMLElement>document.querySelector('#enrollButton')).style.display = 'inline';
            }
          );
        }
        this.likeService.isLiked(this.currentUser.userId, this.contest.contestId).subscribe(
          (data: Like) => {
            (<HTMLElement>document.querySelector('#like')).style.display = 'none';
            (<HTMLElement>document.querySelector('#dislike')).style.display = 'inline';
            this.currentLike = data;
          },
          error => {
            (<HTMLElement>document.querySelector('#like')).style.display = 'inline';
          }
        );
      },
      error => {
        this.notificationService.error("Contest details were not received");
      }
    );
  }

  like() {
    let like: Like = new Like();
    like.contestId = this.contestId;
    like.userId = this.currentUser.userId;
    console.log(like);
    this.likeService.addLike(like).subscribe(
      (data: Like) => {
        (<HTMLElement>document.querySelector('#like')).style.display = 'none';
        (<HTMLElement>document.querySelector('#dislike')).style.display = 'inline';
        this.currentLike = data;
        this.noLikes = this.noLikes + 1;
      },
      error => {
        this.notificationService.error("This contest connot be liked");
      }
    );
  }

  dislike() {
    this.likeService.deleteLike(this.currentLike.userlikeId).subscribe(
      (data: Aux) => {
        (<HTMLElement>document.querySelector('#dislike')).style.display = 'none';
        (<HTMLElement>document.querySelector('#like')).style.display = 'inline';
        this.noLikes = this.noLikes - 1;
      },
      error => {
        this.notificationService.error("This contest could not be disliked");
      }
    );

  }

  openDialog() {
    const dialogRef = this.dialog.open(DialogEnrollComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result == true) {
        (<HTMLElement>document.querySelector('#enrollButton')).style.display = 'none';
        (<HTMLElement>document.querySelector('#unenrollButton')).style.display = 'inline';
      }
    });
  }

  openDialogUnenroll() {
    const dialogRef = this.dialog.open(DialogUnenrollComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result == true) {
        (<HTMLElement>document.querySelector('#unenrollButton')).style.display = 'none';
        (<HTMLElement>document.querySelector('#enrollButton')).style.display = 'inline';
      }
    });
  }

  openDialogContestants() {
    const dialogRef = this.dialog.open(DialogContestantsComponent);

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  openDialogDelete() {
    const dialogRef = this.dialog.open(DialogDeleteComponent);

    dialogRef.afterClosed().subscribe(result => {
    });
  }

}