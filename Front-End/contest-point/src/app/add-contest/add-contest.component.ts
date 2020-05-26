import { Component, OnInit, ContentChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../_models/user';
import { Contest } from '../_models/contest';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-add-contest',
  templateUrl: './add-contest.component.html',
  styleUrls: ['./add-contest.component.scss']
})
export class AddContestComponent implements OnInit {
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  isOptional = true;
  user: User = new User();
  contest: Contest = new Contest();
  dat: Date;

  constructor(private _formBuilder: FormBuilder, private authService: AuthService) { }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      partners: [''],
      enrollmentStartDate: ['', Validators.required],
      enrollmentDueDate: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ''
    });
  }

  formatDate(date: Date): string {
    var s = date.toString().split(" ");
    var aux = "";
    if (s[1] == "Jan")
      aux = "01";
    if (s[1] == "Feb")
      aux = "02";
    if (s[1] == "Mar")
      aux = "03";
    if (s[1] == "Apr")
      aux = "04";
    if (s[1] == "May")
      aux = "05";
    if (s[1] == "Jun")
      aux = "06";
    if (s[1] == "Jul")
      aux = "07";
    if (s[1] == "Aug")
      aux = "08";
    if (s[1] == "Sep")
      aux = "09";
    if (s[1] == "Oct")
      aux = "10";
    if (s[1] == "Nov")
      aux = "11";
    if (s[1] == "Dec")
      aux = "12";
    var result = s[3] + "-" + aux + "-" + s[2];
    return result;
  }

  submit() {
    this.contest.contestName = this.firstFormGroup.value.name;
    this.contest.details = this.firstFormGroup.value.description;
    this.contest.partners = this.firstFormGroup.value.partners;
    var s: string = this.formatDate(this.firstFormGroup.value.startDate);
    this.contest.startDate = s;
    var s: string = this.formatDate(this.firstFormGroup.value.endDate);
    this.contest.endDate = s;
    var s: string = this.formatDate(this.firstFormGroup.value.enrollmentStartDate);
    this.contest.enrollmentStart = s;
    var s: string = this.formatDate(this.firstFormGroup.value.enrollmentDueDate);
    this.contest.enrollmentDue = s;
    this.contest.userId = this.authService.getCurrentUser().userId;
  }

}
