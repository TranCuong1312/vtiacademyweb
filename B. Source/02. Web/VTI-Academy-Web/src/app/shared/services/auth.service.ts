import { ResetRequest } from './../models/reset-request.model';
import { CommonResponseService } from './../../public/common/services/common-response.service';
import { UserService } from './user.service';
import { AuthenticationResponse } from './../models/authentication-response.model';
import { Injectable } from '@angular/core';
import { BehaviorSubject} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import {BaseResponse} from '../../shared/models/common-response.model';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends CommonResponseService{
  // @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
  // @Output() username: EventEmitter<string> = new EventEmitter();

  token: BehaviorSubject<string> = new BehaviorSubject(null);

  constructor(
      protected http: HttpClient,
      private router: Router,
      private userService : UserService,
      private toastr : ToastrService) { 
      super(http);
      this.loadFromLocalStorage();
    }

    login(username: string, password: string) {
      return this.http.post<BaseResponse<AuthenticationResponse>>(`${environment.endpointAPI}` + '/auth/authenticate', { username, password});
    }


    //  async resetPassword(email : any): Promise<BaseResponse<any>>{ 
    //    console.log(email);
    //   return await super.postAsync(`${environment.endpointAPI}` + '/auth/reset-password',{email}) ;
    // }

    resetPassword(email : string){ 
      return this.http.post(`${environment.endpointAPI}` + '/auth/reset-password',{email}) ;
    }


    saveToLocalStorage(token: AuthenticationResponse) {
      localStorage.setItem('refreshToken', JSON.stringify(token.refreshToken));
      localStorage.setItem('token', JSON.stringify(token.authenticationToken));
      localStorage.setItem('userName', JSON.stringify(token.username));
      localStorage.setItem('role',JSON.stringify(token.role));
      // this.loggedIn.emit(true);
      // this.username.emit(token.username);
      this.userService.sendUserInfo(this.getUsername());
      this.checkAndNavigate();
    }

    loadFromLocalStorage() {
      const tokens = localStorage.getItem('token');
      if (tokens) {
        this.token.next(tokens);
      }
    }

    // async deleteRefreshToken(){
    //   return await super.postAsync(`account/logout`,{username : this.getUsername(), refreshToken : this.getRefreshToken()});
    // }

     deleteRefreshToken(){
       let header: HttpHeaders = new HttpHeaders();
       header = header.set('Authorization',`Bearer ${this.getToken()}`);
       
      return this.http.post<BaseResponse<any>>(`${environment.endpointAPI}` + '/account/logout'
             , {username : this.getUsername(), refreshToken : this.getRefreshToken()}, {headers : header});
    }

    logout() {
      this.deleteRefreshToken().subscribe(
        (data) => {
          if(data.type !== "ERROR"){
            localStorage.removeItem('token');
            localStorage.removeItem('userName');
            localStorage.removeItem('role');
            localStorage.removeItem('refreshToken');
            this.showToastr(4,"Logout successful", "Logout");
          }
        },
        (error) => {
          this.showToastr(2,"Something wrong :<","Server not response")
        }
      );
      
    }

    getToken(): string {
      if(this.isLoggedIn()){
        if(localStorage.getItem('token').startsWith('\"')){
          return localStorage.getItem('token').substring(1,localStorage.getItem('token').length-1);
        }
        return localStorage.getItem('token');
      }
    }

    getRefreshToken(): string {
      if(this.isLoggedIn()){
        if(localStorage.getItem('refreshToken').startsWith('\"')){
          return localStorage.getItem('refreshToken').substring(1,localStorage.getItem('refreshToken').length-1);
        }
        return localStorage.getItem('refreshToken');
      }
    }

    getUsername(){
      if(this.isLoggedIn()){
        if(localStorage.getItem('userName').startsWith('\"')){
          return localStorage.getItem('userName').substring(1,localStorage.getItem('userName').length-1);
        }
        return localStorage.getItem('userName');
      }
      
    }

    isLoggedIn() : boolean{
      return localStorage.getItem('token') != null;
    }

    checkAndNavigate(){ 
      if(this.router.url == '/admin/login'){
        if(localStorage.getItem('role') === '"USER"'){
          this.router.navigateByUrl('/');
        }
        else{
          this.router.navigateByUrl('/admin');
        }
      }
      else{
        this.router.navigateByUrl('/');
      }
    }

    showToastr(type : number,message : string, title : string){
      switch(type){
        case 1 : {
          this.toastr.success(message, title);
          break;
        }
        case 2 : {
          this.toastr.error(message, title);
          break;
        }
        case 3 : {
          this.toastr.warning(message, title);
          break;
        }
        case 4 : {
          this.toastr.info(message, title);
          break;
        }
      }
    }

}
