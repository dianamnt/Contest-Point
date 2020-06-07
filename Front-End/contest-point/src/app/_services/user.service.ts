import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../_models/user';
import { baseUrl } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  token: any;

  constructor(private http: HttpClient) { }

  updateUser(user: User) {
    this.token = localStorage.getItem('token').toString();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.token
    });
    const options = { headers };
    const body = JSON.stringify(user);
    return this.http.post(baseUrl + 'user/updateUser', body, options);
  }
}
