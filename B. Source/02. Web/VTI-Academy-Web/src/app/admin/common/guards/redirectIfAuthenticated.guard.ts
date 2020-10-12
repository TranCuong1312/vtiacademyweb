import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable()
export class RedirectIfAuthenticatedGuard implements CanActivate {
  constructor(private router: Router) {}
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | import("@angular/router").UrlTree | Observable<boolean | import("@angular/router").UrlTree> | Promise<boolean | import("@angular/router").UrlTree> {
    if (localStorage.getItem('token')) {
      if(localStorage.getItem('role') === '"ADMIN"'){
         this.router.navigateByUrl('/admin');
      }else{
        this.router.navigateByUrl('/');
      }
      return false;
      
    } else {
      return true;
    }
  }
}
