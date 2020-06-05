import { Injectable } from '@angular/core';
import { Contest } from '../_models/contest';
import { ContestDetailed } from '../_models/contestdetailed';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { baseUrl } from '../../environments/environment';
import { Aux } from '../_models/auxiliary';

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

  deleteContest(userId: number, contestId: number) {
    this.token = localStorage.getItem('token').toString();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.token
    });
    const options = { headers };
    let aux: Aux = new Aux();
    aux.firstSensitiveDataParam = userId;
    aux.secondSensitiveDataParam = contestId;
    const body = JSON.stringify(aux);
    return this.http.post(baseUrl + 'contest/deleteContest', body, options);
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

  getContests() {
    this.token = localStorage.getItem('token').toString();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.token
    });
    const options = { headers };
    return this.http.get(baseUrl + 'contest/listDetailed', options);
  }

  getMyContests(userId: number) {
    this.token = localStorage.getItem('token').toString();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.token
    });
    const options = { headers };
    return this.http.get(baseUrl + 'contest/listMyContests?userId=' + userId.toString(), options);
  }
}
