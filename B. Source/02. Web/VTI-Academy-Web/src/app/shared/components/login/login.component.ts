import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { AuthenticationResponse } from '../../models/authentication-response.model';
declare var $ : any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit,AfterViewInit {

  loginForm : FormGroup;
  resetForm : FormGroup;
  isError: boolean;
  isLoginAdmin : boolean;
  isClickLogin : boolean;
  isClickReset : boolean;

  constructor(private build : FormBuilder
      , private authService : AuthService
      , private router : Router) { }

  ngOnInit(): void {
    if(this.router.url == '/admin/login'){
      this.isLoginAdmin = true;
    }else{
      this.isLoginAdmin = false;
    }
    this.loginForm = this.build.group({
      username : [''],
      password : [''],
    });
    this.resetForm = this.build.group({
      email : ['',[Validators.email,Validators.required]],
    });
  }

  ngAfterViewInit(): void{
    $('#exampleModal').on('show.bs.modal', function (event) {
      var button = $(event.relatedTarget) 
      var recipient = button.data('whatever') 
      var modal = $(this)
      modal.find('.modal-title').text('New message to ' + recipient)
      modal.find('.modal-body input').val(recipient)
    });
  }

  loginSubmit(){
    this.isClickLogin = true;
    setTimeout(() => {
      this.authService.login(this.username.value
        ,this.password.value).subscribe(data => {
          if(data){
            this.isError = false;
            this.authService.saveToLocalStorage(new AuthenticationResponse(data.data));
            this.authService.showToastr(1,'Hello ' + data.data.username,'Login Success');
          }} ,

          (error) => {
            this.isError = true;
            this.authService.showToastr(2,'Please check your credential','Login Fail');
          });
        this.isClickLogin = false;
    }, 2000);

  }

  // async resetPassword(): Promise<void>{
  //    await this.authService.resetPassword((this.email.value));
  // }

  async resetPassword(){
    this.isClickReset = true;
    setTimeout(() => {
      this.authService.resetPassword(this.email.value).subscribe(data => {
        if(data){
          this.authService.showToastr(1,'Please check your email for new password','Reset Password Success');
          $('#resetPass').modal('hide');
        }
      },
        (error) => {
          this.authService.showToastr(3,'The email is not right','Something Wrong :<');
        });
      this.isClickReset = false;
    }, 2000);
    
  }

  checkError(){
    if(this.email.invalid && (this.email.dirty || this.email.touched)){
      return true;
    } 
  }

  get username(){
    return this.loginForm.get('username');
  }

  get password(){
    return this.loginForm.get('password');
  }

  get email(){
    return this.resetForm.get('email');
  }
}
