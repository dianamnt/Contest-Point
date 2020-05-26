import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../_services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(public auth: AuthService, public router: Router) { }

  canActivate(): boolean {
    if (!this.auth.isloggedIn()) {
      this.router.navigate(['/welcome']);
      return false;
    }
    return true;
  }
}
