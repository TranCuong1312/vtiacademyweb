import { Contact } from './../../../shared/models/contact.model';
import { ContactService } from './../../common/services/contact.service';
import { UserService } from '../../../shared/services/user.service';
import { AuthService } from './../../../shared/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

declare var $:any;
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  username : string;
  isLoggedIn = false;
  isClickLogout : boolean = false;
  subscription : Subscription;
  contact: Contact;
  constructor(private authService : AuthService
          , private router : Router, private userService : UserService,private contactService : ContactService) {}


  ngOnInit(): void {
    this.getContact();
    $(window).on('scroll', function(){
      if($(window).scrollTop()){
        $('#menu').addClass('bg-header');
      } else{
        $('#menu').removeClass('bg-header');
      }
    });

    this.subscription = this.userService.getUserInfo().subscribe(data => {
      this.username = data.userInfo;
      this.isLoggedIn = true;
    })

    if(this.authService.getToken()){
      this.isLoggedIn = this.authService.isLoggedIn();
      this.username = this.authService.getUsername();
    }

  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  logout(){
    this.isClickLogout = true;
    setTimeout(() => {
      this.authService.logout();
      this.isLoggedIn = false;
      if(this.router.url.includes('/admin/')){
        this.router.navigateByUrl('/admin/login');   
      }
      else {
        this.router.navigateByUrl(this.router.url)
      };
      this.isClickLogout = false;
    }, 2000);
    
  }

  async getContact(): Promise<void>{
    let result = await this.contactService.getContact(null);
    this.contact = result.data[0];
  }
}
