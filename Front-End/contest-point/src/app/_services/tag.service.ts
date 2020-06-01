import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseUrl } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TagService {
  token: any;

  constructor(private http: HttpClient) { }

  getTags() {
    this.token = localStorage.getItem('token').toString();
    const headers = new HttpHeaders({
      'Authorization': this.token
    });
    const options = { headers };
    return this.http.get(baseUrl + 'tag/list', options);
  }
}
