import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../_services/notification.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  centered = false;
  disabled = false;
  unbounded = false;

  constructor(private notificationService: NotificationService) { }

  ngOnInit() {
    
  }

}
