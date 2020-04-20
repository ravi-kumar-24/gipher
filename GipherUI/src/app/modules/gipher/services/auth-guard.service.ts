import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/auth.service';
import { CanActivate } from '@angular/router/src/interfaces';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(
    private authService: AuthService,
    private router: Router) { }

  canActivate() {
    console.log("inside canactivate");
    if (this.authService.isTokenExpired()) {
      return true;
    }
    console.log("inside canactivate routing");
    this.router.navigate(['/login']);
    return false;
  }

}
