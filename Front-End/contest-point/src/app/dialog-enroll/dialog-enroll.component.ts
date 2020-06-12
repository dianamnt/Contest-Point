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
  selector: 'app-dialog-enroll',
  templateUrl: './dialog-enroll.component.html',
  styleUrls: ['./dialog-enroll.component.scss']
})
export class DialogEnrollComponent implements OnInit {
  requirements: Requirement[];
  details: Detail[] = [];
  contestId: number;
  currentUser: User;
  contestName: string;
  checked: false;
  contract: ContractDetailed = new ContractDetailed();
  selectedFiles: FileList;
  fileList: File[] = [];
  valid: boolean = false;

  constructor(private notificationService: NotificationService, private contractService: ContractService, private router: Router, private contestService: ContestService, private authService: AuthService) { }

  ngOnInit(): void {
    this.contestId = parseInt(localStorage.getItem('currentContest'));
    this.currentUser = this.authService.getCurrentUser();
    this.contestService.findByIdDetailed(this.contestId).subscribe(
      (data: ContestDetailed) => {
        this.requirements = data.requirements;
        this.contestName = data.contestName;
        var detail: Detail = new Detail();
        var i;
        for (i = 0; i < this.requirements.length; i++) {
          detail.orderNo = this.requirements[i].orderNo;
          this.details.push(JSON.parse(JSON.stringify(detail)));
        }
      },
      error => {
        this.notificationService.error("Requirements not received!");
      }
    );
  }

  trackByIdx(index: number, obj: any): any {
    return index;
  }

  selectFile(event, i: number) {
    this.selectedFiles = event.target.files;
    console.log(this.selectedFiles);
    this.details[i].imageContent = this.selectedFiles[0].name;
    this.fileList.push(this.selectedFiles.item(0));
    console.log(this.fileList);
  }

  submit() {
    console.log(this.details);
    this.contract.details = this.details;
    this.contract.contestId = this.contestId;
    this.contract.userId = this.currentUser.userId;
    this.contract.userFirstName = this.currentUser.firstName;
    this.contract.userLastName = this.currentUser.lastName;
    this.contract.userEmail = this.currentUser.email;
    this.contractService.enroll(this.contract).subscribe(
      (data: ParticipationContract) => {
        this.notificationService.success(this.currentUser.firstName + " " + this.currentUser.lastName + " enrolled to " + this.contestName);
        this.valid = true;
      },
      error => {
        this.notificationService.error("Enrollment did not work!");
        
      });
    let i;
    for (i = 0; i < this.fileList.length; i++) {
      this.contractService.upload(this.fileList[i]).subscribe(
        (data: any) => {

        },
        error => {
          this.notificationService.error("Files were not uploaded. We suggest you unenroll and enroll again.");
          
        }
      )
    }
  }

}
