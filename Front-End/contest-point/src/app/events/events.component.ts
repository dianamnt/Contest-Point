import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../_services/notification.service';
import { ContestDetailed } from '../_models/contestdetailed';
import { ContestService } from '../_services/contest.service';
import { AuthService } from '../_services/auth.service';
import { Router } from '@angular/router';
import { User } from '../_models/user';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {
  centered = false;
  disabled = false;
  unbounded = false;
  currentUser: User;
  contests: ContestDetailed[] = [];
  contestsEnrolled: ContestDetailed[] = [];
  contestsEnrolledUpcoming: ContestDetailed[] = [];

  constructor(private notificationService: NotificationService, private contestService: ContestService, private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    this.contestService.getMyContests(this.currentUser.userId).subscribe(
      (data: ContestDetailed[]) => {
        this.contests = data;
      },
      error => {
        this.notificationService.error("Data could not be retrieved!");
      });
    this.contestService.getContestsEnrolled(this.currentUser.userId).subscribe(
      (data: ContestDetailed[]) => {
        this.contestsEnrolled = data;
      },
      error => {
        this.notificationService.error("Data could not be retrieved!");
      });
    this.contestService.getContestsEnrolledUpcoming(this.currentUser.userId).subscribe(
      (data: ContestDetailed[]) => {
        this.contestsEnrolledUpcoming = data;
      },
      error => {
        this.notificationService.error("Data could not be retrieved!");
      });
  }

  goToCurrentContest(contestId: number) {
    localStorage.setItem('currentContest', contestId.toString());
    this.router.navigate(['/contest']);
  }

  data(smth: any) {
    localStorage.setItem('currentTag', smth.toString());
    this.router.navigate(['/tag']);
  }

}
