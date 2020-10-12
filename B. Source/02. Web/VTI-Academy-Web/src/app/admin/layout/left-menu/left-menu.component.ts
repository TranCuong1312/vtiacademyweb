import { AuthService } from './../../../shared/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
declare var $:any;
@Component({
  selector: 'app-left-menu',
  templateUrl: 'left-menu.component.html',
  styleUrls: ['./left-menu.component.scss']
})
export class LeftMenuComponent implements OnInit {

  constructor(private authService : AuthService, private router : Router) { }

  ngOnInit(): void {
  }

  logout(){
    setTimeout(() => {
      this.authService.logout();
      if(this.router.url.includes('/admin')){
        this.router.navigateByUrl('/admin/login');   
      }
      else {
        this.router.navigateByUrl(this.router.url)
      };
    }, 2000);
    
  }
}

