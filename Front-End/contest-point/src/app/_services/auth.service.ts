import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../_models/user';
import { map } from 'rxjs/operators'
import { baseUrl } from 'src/environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt'

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  jwtHelper = new JwtHelperService;
  decodedToken: any;
  token: any;

  constructor(private http: HttpClient) { }

  login(user: User) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const options = { headers };
    const body = JSON.stringify(user);
    return this.http.post(baseUrl + 'auth/login', body, options);
  }

  register(user: User) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const options = { headers };
    const body = JSON.stringify(user);
    return this.http.post(baseUrl + 'auth/register', body, options);
  }

  isloggedIn() {
    this.token = localStorage.getItem('token').toString();
    if(this.token == null)
      return false;
    if(this.token == "")
      return false;
    return !this.jwtHelper.isTokenExpired(this.token);
  }

  getCurrentUser(): User {
    var angular: any;
    this.token = localStorage.getItem('token').toString();
    this.decodedToken = this.jwtHelper.decodeToken(this.token);
    const user: User = {
      userId: this.decodedToken.userId,
      firstName: this.decodedToken.firstName,
      lastName: this.decodedToken.lastName,
      email: this.decodedToken.sub,
      password: null,
      institutionName: this.decodedToken.institutionName,
      profilePicture: this.decodedToken.profilePicture
    }
    return user;
  }

}
