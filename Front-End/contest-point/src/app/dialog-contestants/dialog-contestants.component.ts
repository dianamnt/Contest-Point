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

@Component({
  selector: 'app-dialog-contestants',
  templateUrl: './dialog-contestants.component.html',
  styleUrls: ['./dialog-contestants.component.scss']
})
export class DialogContestantsComponent implements OnInit {
  details: Detail[] = [];
  contestId: number;
  currentUser: User;
  contestName: string;
  contracts: ContractDetailed[] = [];

  constructor(private notificationService: NotificationService, private contractService: ContractService, private router: Router, private contestService: ContestService, private authService: AuthService) { }

  ngOnInit(): void {
    this.contestId = parseInt(localStorage.getItem('currentContest'));
    this.currentUser = this.authService.getCurrentUser();
    this.contestService.findByIdDetailed(this.contestId).subscribe(
      (data: ContestDetailed) => {
        this.contestName = data.contestName;
      },
      error => {
        this.notificationService.error("Requirements not received!");
      }
    );
    this.contractService.getContracts(this.currentUser.userId, this.contestId).subscribe(
      (data: ContractDetailed[]) => {
        this.contracts = data;
        console.log(this.contracts);
      },
      error => {

      }
    );
  }

}
