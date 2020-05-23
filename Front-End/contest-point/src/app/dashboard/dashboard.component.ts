import { Component, OnInit } from '@angular/core';
import { AlertifyService } from '../_services/alertify.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(private alertifyService: AlertifyService) { }

  ngOnInit() {
    this.alertifyService.success("Buna siua coaie");
  }

}
