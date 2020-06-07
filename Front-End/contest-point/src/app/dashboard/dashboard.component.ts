import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../_services/notification.service';
import { ContestDetailed } from '../_models/contestdetailed';
import { ContestService } from '../_services/contest.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  centered = false;
  disabled = false;
  unbounded = false;
  contests: ContestDetailed[] = [];
  contestsTrending: ContestDetailed[] = [];
  searchValue: any;

  constructor(private notificationService: NotificationService, private contestService: ContestService, private router: Router) { }

  ngOnInit() {
    this.contestService.getContests().subscribe(
      (data: ContestDetailed[]) => {
        this.contests = data;
      },
      error => {
        this.notificationService.error("Data could not be retrieved!");
      });

    this.contestService.trendingContests().subscribe(
      (data: ContestDetailed[]) => {
        this.contestsTrending = data;
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
