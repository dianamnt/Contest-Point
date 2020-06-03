import { Injectable } from '@angular/core';
import { Contest } from '../_models/contest';
import { ContestDetailed } from '../_models/contestdetailed';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { baseUrl } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ContestService {
  token: any;

  constructor(private http: HttpClient) { }

  addContest(contest: Contest) {
    this.token = localStorage.getItem('token').toString();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.token
    });
    const options = { headers };
    const body = JSON.stringify(contest);
    return this.http.post(baseUrl + 'contest/saveContest', body, options);
  }

  addContestDetailed(contest: ContestDetailed) {
    this.token = localStorage.getItem('token').toString();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.token
    });
    const options = { headers };
    const body = JSON.stringify(contest);
    return this.http.post(baseUrl + 'contest/saveContestDetailed', body, options);
  }

  getContests() {
    this.token = localStorage.getItem('token').toString();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.token
    });
    const options = { headers };
    return this.http.get(baseUrl + 'contest/listDetailed', options);
  }

  findByIdDetailed(id: number) {
    this.token = localStorage.getItem('token').toString();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.token
    });
    const options = { headers };
    const body = JSON.stringify(id);
    return this.http.post(baseUrl + 'contest/findByIdDetailed',body, options);
  }
}
