import { Component, OnInit, ContentChild, ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { User } from '../_models/user';
import { Contest } from '../_models/contest';
import { AuthService } from '../_services/auth.service';
import { ContestService } from '../_services/contest.service';
import { TagService } from '../_services/tag.service';
import { NotificationService } from '../_services/notification.service';
import { ContestDetailed } from '../_models/contestdetailed';
import { Location } from '../_models/location';
import { Tag } from '../_models/tag';
import { Requirement } from '../_models/requirement';
import { Observable } from 'rxjs';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatAutocompleteSelectedEvent, MatAutocomplete } from '@angular/material/autocomplete';
import { map, startWith } from 'rxjs/operators';
import { MatChipInputEvent } from '@angular/material/chips';
import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-contest',
  templateUrl: './add-contest.component.html',
  styleUrls: ['./add-contest.component.scss']
})
export class AddContestComponent implements OnInit {
  checked = false;
  display = false;

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  isOptional = true;
  user: User = new User();
  contest: ContestDetailed = new ContestDetailed();
  image: string;
  location: Location = new Location();
  tags: Tag[] = [];
  requirements: Requirement[] = [];
  labelPosition: 'before' | 'after' = 'after';

  visible = true;
  selectable = true;
  removable = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  tagCtrl = new FormControl();
  filteredTags: Observable<string[]>;
  stringTags: string[] = [];
  allTags: string[] = [];

  stringRequirements = [];

  @ViewChild('tagInput') tagInput: ElementRef<HTMLInputElement>;
  @ViewChild('auto') matAutocomplete: MatAutocomplete;

  constructor(private _formBuilder: FormBuilder, private authService: AuthService, private contestService: ContestService, private notifService: NotificationService, private tagService: TagService, private router: Router) {
    this.filteredTags = this.tagCtrl.valueChanges.pipe(
      startWith(null),
      map((tag: string | null) => tag ? this._filter(tag) : this.allTags.slice()));
  }

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
      onlineResource: [''],
      streetName: [''],
      streetNumber: [''],
      buildingNumber: [''],
      postalCode: [''],
      city: [''],
      region: [''],
      country: [''],
      details: [''],
    });
    this.thirdFormGroup = this._formBuilder.group({
      requirement: [''],
    });
    this.tagService.getTags().subscribe((data: Tag[]) => {
      var i;
      for (i = 0; i < data.length; i++) {
        this.allTags.push(data[i].tagName);
      }
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

  onlineClicked() {
    (<HTMLElement>document.querySelector('#online__div')).style.display = 'inline';
    (<HTMLElement>document.querySelector('#location__div')).style.display = 'none';
    (<HTMLElement>document.querySelector('#location__div2')).style.display = 'none';
    (<HTMLElement>document.querySelector('#location__div3')).style.display = 'none';
  }

  onsiteClicked() {
    (<HTMLElement>document.querySelector('#online__div')).style.display = 'none';
    (<HTMLElement>document.querySelector('#location__div')).style.display = 'block';
    (<HTMLElement>document.querySelector('#location__div2')).style.display = 'block';
    (<HTMLElement>document.querySelector('#location__div3')).style.display = 'block';
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      this.stringTags.push(value.trim());
    }

    if (input) {
      input.value = '';
    }

    this.tagCtrl.setValue(null);
  }

  remove(tag: string): void {
    const index = this.stringTags.indexOf(tag);

    if (index >= 0) {
      this.stringTags.splice(index, 1);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.stringTags.push(event.option.viewValue);
    this.tagInput.nativeElement.value = '';
    this.tagCtrl.setValue(null);
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.allTags.filter(tag => tag.toLowerCase().indexOf(filterValue) === 0);
  }

  addRequirement() {
    this.stringRequirements.push(this.thirdFormGroup.value.requirement);
    (<HTMLElement>document.querySelector('#reqList')).style.display = 'block';
    this.thirdFormGroup.reset();
  }

  close(requirement: string) {
    const index = this.stringRequirements.indexOf(requirement);
    if (index > -1) {
      this.stringRequirements.splice(index, 1);
    }
    if(this.stringRequirements.length == 0) {
      (<HTMLElement>document.querySelector('#reqList')).style.display = 'none';
    }
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.stringRequirements, event.previousIndex, event.currentIndex);
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
    this.contest.coverPicture = this.image;
    this.contest.userId = this.authService.getCurrentUser().userId;

    this.location.onlineResource = this.secondFormGroup.value.onlineResource;
    this.location.streetName = this.secondFormGroup.value.streetName;
    this.location.streetNumber = this.secondFormGroup.value.streetNumber;
    this.location.buildingNumber = this.secondFormGroup.value.buildingNumber;
    this.location.postalCode = this.secondFormGroup.value.postalCode;
    this.location.details = this.secondFormGroup.value.details;
    this.location.city = this.secondFormGroup.value.city;
    this.location.region = this.secondFormGroup.value.region;
    this.location.country = this.secondFormGroup.value.country;

    var locations: Location[] = [];
    locations.push(this.location);
    this.contest.locations = locations;

    var tag: Tag = new Tag();
    var i;
    for (i = 0; i < this.stringTags.length; i++) {
      tag.tagName = this.stringTags[i];
      this.tags.push(JSON.parse(JSON.stringify(tag)));
    }
    this.contest.tags = this.tags;

    var req: Requirement = new Requirement();
    var i;
    var counter = 1;
    for (i = 0; i < this.stringRequirements.length; i++) {
      req.content = this.stringRequirements[i];
      req.isMandatory = 1;
      req.reqImage = 0;
      req.orderNo = counter;
      this.requirements.push(JSON.parse(JSON.stringify(req)));
      counter = counter + 1;
    }
    this.contest.requirements = this.requirements;

    this.contestService.addContestDetailed(this.contest).subscribe(
      (data: Contest) => {
        this.notifService.success("Contest created!");
        this.router.navigate(['/my-contests']);
      },
      error => {
        this.notifService.error("Contest could not be created!");
      }
    )
  }

}
