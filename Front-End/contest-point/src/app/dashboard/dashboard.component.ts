import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../_services/notification.service';
import { ContestDetailed } from '../_models/contestdetailed';
import { ContestService } from '../_services/contest.service'

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

  constructor(private notificationService: NotificationService, private contestService: ContestService) { }

  ngOnInit() {
    this.contestService.getContests().subscribe(
      (data: ContestDetailed[]) => {
        this.contests = data;
      },
      error => {
        this.notificationService.error("Data could not be retrieved!");
      });
  }

  like() {
    (<HTMLElement>document.querySelector('#like')).style.display = 'none';
    (<HTMLElement>document.querySelector('#dislike')).style.display = 'inline';
  }

  dislike() {
    (<HTMLElement>document.querySelector('#dislike')).style.display = 'none';
    (<HTMLElement>document.querySelector('#like')).style.display = 'inline';
  }

}
