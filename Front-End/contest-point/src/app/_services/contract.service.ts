import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { baseUrl } from '../../environments/environment';
import { ContractDetailed } from '../_models/contractdetailed';
import { Aux } from '../_models/auxiliary';

@Injectable({
  providedIn: 'root'
})
export class ContractService {
  token: any;

  constructor(private http: HttpClient) { }

  enroll(contract: ContractDetailed) {
    this.token = localStorage.getItem('token').toString();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.token
    });
    const options = { headers };
    const body = JSON.stringify(contract);
    return this.http.post(baseUrl + 'contract/enroll', body, options);
  }

  deleteContract(contractId: number) {
    this.token = localStorage.getItem('token').toString();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.token
    });
    const options = { headers };
    let aux: Aux = new Aux();
    aux.firstSensitiveDataParam = contractId;
    const body = JSON.stringify(aux);
    return this.http.post(baseUrl + 'contract/deleteContract', body, options);
  }

  isEnrolled(userId: number, contestId: number) {
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
    return this.http.post(baseUrl + 'contract/isEnrolled', body, options);
  }

  getContracts(userId: number, contestId: number) {
    this.token = localStorage.getItem('token').toString();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.token
    });
    const options = { headers };
    return this.http.get(baseUrl + 'contract/listDetailed?userId=' + userId.toString() + '&contestId=' + contestId.toString(),options);
  }
}