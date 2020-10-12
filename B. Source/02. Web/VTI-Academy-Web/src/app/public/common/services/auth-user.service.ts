import { Injectable } from '@angular/core';
import { BehaviorSubject} from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthUserService {
  token: BehaviorSubject<string> = new BehaviorSubject(null);

  constructor(private router: Router  ) 
  { 
    this.loadFromLocalStorage();
  }

    loadFromLocalStorage() {
      const tokens = localStorage.getItem('token');
      if (tokens) {
        this.token.next(tokens);
      }
    }

    logout() {
      localStorage.removeItem('token');
      localStorage.removeItem('userName');
      localStorage.removeItem('role');
      this.router.navigateByUrl('/');
    }

}
