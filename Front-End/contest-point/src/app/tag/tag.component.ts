import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../_services/notification.service';
import { ContestDetailed } from '../_models/contestdetailed';
import { ContestService } from '../_services/contest.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-tag',
  templateUrl: './tag.component.html',
  styleUrls: ['./tag.component.scss']
})
export class TagComponent implements OnInit {
  centered = false;
  disabled = false;
  unbounded = false;
  contests: ContestDetailed[] = [];
  tagName: string;
  contestsForYou: ContestDetailed[] = [];

  constructor(private notificationService: NotificationService, private contestService: ContestService, private router: Router) { }

  ngOnInit(): void {
    this.tagName = localStorage.getItem('currentTag').toString();
    this.contestService.filterByTag(this.tagName).subscribe(
      (data: ContestDetailed[]) => {
        this.contests = data;
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
    this.tagName = localStorage.getItem('currentTag').toString();
    this.contestService.filterByTag(this.tagName).subscribe(
      (data: ContestDetailed[]) => {
        this.contests = data;
      },
      error => {
        this.notificationService.error("Data could not be retrieved!");
      });
  }


}
