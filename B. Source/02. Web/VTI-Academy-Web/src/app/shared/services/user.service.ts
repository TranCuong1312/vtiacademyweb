import { Injectable } from '@angular/core';
import {Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private subject = new Subject<any>();

  constructor() { }

  sendUserInfo(data : any){
    this.subject.next({userInfo : data});
  }

  getUserInfo() : Observable<any>{
    return this.subject.asObservable();
  }

}
