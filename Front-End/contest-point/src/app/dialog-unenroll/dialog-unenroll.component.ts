import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../_services/notification.service';
import { ContractService } from '../_services/contract.service';
import { ContestService } from '../_services/contest.service';
import { ParticipationContract } from '../_models/participationcontract';
import { AuthService } from '../_services/auth.service';
import { User } from '../_models/user';
import { Aux } from '../_models/aux';
import { ContestDetailed } from '../_models/contestdetailed';

@Component({
  selector: 'app-dialog-unenroll',
  templateUrl: './dialog-unenroll.component.html',
  styleUrls: ['./dialog-unenroll.component.scss']
})
export class DialogUnenrollComponent implements OnInit {
  currentUser: User;
  contestId: number;
  pc: ParticipationContract = new ParticipationContract();
  contestName: string;

  constructor(private notificationService: NotificationService, private contractService: ContractService, private contestService: ContestService, private authService: AuthService) { }

  ngOnInit(): void {
    this.contestId = parseInt(localStorage.getItem('currentContest'));
    this.currentUser = this.authService.getCurrentUser();
    this.contractService.isEnrolled(this.currentUser.userId, this.contestId).subscribe(
      (data: ParticipationContract) => {
        this.pc = data;
      }
    );
    this.contestService.findByIdDetailed(this.contestId).subscribe(
      (data: ContestDetailed) => {
        this.contestName = data.contestName;
      },
      error => {
        this.notificationService.error("Contest details not received!");
      }
    );
  }

  submit() {
    this.contractService.deleteContract(this.pc.pcId).subscribe(
      (data: Aux) => {
        this.notificationService.success("You unenrolled from " + this.contestName);
      },
      error => {
        this.notificationService.error("you could not be unenrolled from " + this.contestName);
      }
    );
  }

}
