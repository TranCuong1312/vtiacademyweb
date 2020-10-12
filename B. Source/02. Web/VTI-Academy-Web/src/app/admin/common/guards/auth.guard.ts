import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Router, CanActivateChild } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable()
export class AuthGuard implements CanActivateChild {
  constructor(private router: Router) {

  }
    canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | import("@angular/router").UrlTree | Observable<boolean | import("@angular/router").UrlTree> | Promise<boolean | import("@angular/router").UrlTree> {
        if (window.localStorage.getItem('token') ) {
          if(localStorage.getItem('role') === '"ADMIN"'){
            return true;
          }else{
            this.router.navigateByUrl('/');
          }
          
        } else {
          this.router.navigateByUrl('/admin/login');
        }
    }
   
}