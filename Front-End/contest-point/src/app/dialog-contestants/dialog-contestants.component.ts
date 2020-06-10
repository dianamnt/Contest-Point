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
import { Requirement } from '../_models/requirement';
import { Detail } from '../_models/detail';
import { ParticipationContract } from '../_models/participationcontract';
import { Sort } from '@angular/material/sort';

@Component({
  selector: 'app-dialog-contestants',
  templateUrl: './dialog-contestants.component.html',
  styleUrls: ['./dialog-contestants.component.scss']
})
export class DialogContestantsComponent implements OnInit {
  details: Detail[] = [];
  contestId: number;
  currentUser: User;
  contestUserId: any;
  contestName: string;
  contracts: ContractDetailed[] = [];
  requirements: Requirement[] = [];
  sortedData: ContractDetailed[];


  constructor(private notificationService: NotificationService, private contractService: ContractService, private router: Router, private contestService: ContestService, private authService: AuthService) { }

  ngOnInit(): void {
    this.contestId = parseInt(localStorage.getItem('currentContest'));
    this.currentUser = this.authService.getCurrentUser();
    this.contestService.findByIdDetailed(this.contestId).subscribe(
      (data: ContestDetailed) => {
        this.contestName = data.contestName;
        this.requirements = data.requirements;
        this.contestUserId = data.userId;
        if (this.currentUser.userId == this.contestUserId) {
          this.contractService.getContracts(this.currentUser.userId, this.contestId).subscribe(
            (data: ContractDetailed[]) => {
              this.contracts = data;
              this.sortedData = this.contracts.slice();
            },
            error => {

            }
          );
        }
      },
      error => {
        this.notificationService.error("Requirements not received!");
      }
    );
  }

  sortData(sort: Sort) {
    const data = this.contracts.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      if (sort.active == "firstName") {
        return compare(a.userFirstName, b.userFirstName, isAsc);
      }
      else if (sort.active == "lastName") {
        return compare(a.userLastName, b.userLastName, isAsc);
      }
      else if (sort.active == "email") {
        return compare(a.userEmail, b.userEmail, isAsc);
      }
      else {
        let i;
        for (i = 0; i <= this.requirements.length; i++) {
          if (this.requirements[i].content == sort.active) {
            return compare(a.details[i].textContent, b.details[i].textContent, isAsc);
          }
        }
        return 0;
      }

    });
  }

  download(fileName: string) {
    let url = "http://localhost:8080/files/files/" + fileName;
    window.open(url);
  }

}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
