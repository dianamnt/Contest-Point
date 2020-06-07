import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../_services/notification.service';
import { ContestDetailed } from '../_models/contestdetailed';
import { ContestService } from '../_services/contest.service';
import { AuthService } from '../_services/auth.service';
import { Router } from '@angular/router';
import { User } from '../_models/user';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.scss']
})
export class FavoritesComponent implements OnInit {
  centered = false;
  disabled = false;
  unbounded = false;
  currentUser: User;
  contestsLiked: ContestDetailed[] = [];
  contestsLikedUpcoming: ContestDetailed[] = [];
  searchValue: any;

  constructor(private notificationService: NotificationService, private contestService: ContestService, private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    this.contestService.getContestsLiked(this.currentUser.userId).subscribe(
      (data: ContestDetailed[]) => {
        this.contestsLiked = data;
      },
      error => {
        this.notificationService.error("Data could not be retrieved!");
      });
    this.contestService.getContestsLikedUpcoming(this.currentUser.userId).subscribe(
      (data: ContestDetailed[]) => {
        this.contestsLikedUpcoming = data;
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
