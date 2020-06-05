import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { baseUrl } from '../../environments/environment';
import { Aux } from '../_models/aux'
import { Like } from '../_models/like';

@Injectable({
  providedIn: 'root'
})
export class LikeService {
  token: any;

  constructor(private http: HttpClient) { }

  addLike(like: Like) {
    this.token = localStorage.getItem('token').toString();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.token
    });
    const options = { headers };
    const body = JSON.stringify(like);
    return this.http.post(baseUrl + 'like/saveLike', body, options);
  }

  deleteLike(likeId: number) {
    this.token = localStorage.getItem('token').toString();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.token
    });
    const options = { headers };
    let aux: Aux = new Aux();
    aux.firstSensitiveDataParam = likeId;
    const body = JSON.stringify(aux);
    return this.http.post(baseUrl + 'like/deleteLike', body, options);
  }

  isLiked(userId: number, contestId: number) {
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
    return this.http.post(baseUrl + 'like/isLiked', body, options);
  }
}
