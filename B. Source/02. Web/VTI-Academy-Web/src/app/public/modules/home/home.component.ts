import { Component, OnInit, AfterViewInit } from '@angular/core';
import { News } from '../../../shared/models/news.model';
import { NewsService } from '../../common/services/news.service';
import { environment } from 'src/environments/environment';
import { IntroService } from '../../common/services/intro.service';
import { Intro } from 'src/app/shared/models/intro.model';
import { EnterpriseService } from '../../common/services/enterprise.service';
import { Enterprise } from 'src/app/shared/models/enterprise.model';
import { OtherConfigService } from '../../common/services/other-config.service';
import { OtherConfig } from 'src/app/shared/models/other-config.model';
declare var $: any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  listNews: News[] =[];
  otherConfig: OtherConfig;
  close: string;
  serverLink: string;
  listIntro: Intro[] = [];

  constructor(
    private newsService: NewsService,
    private introService: IntroService,
    private enterpriseService : EnterpriseService,
    private otherConfigService: OtherConfigService
  ) { }

  ngOnInit(): void {
    this.serverLink = environment.endpointAPI;
    this.getListNews();
    this.getOtherConfig();
    this.getListintro();
    if (!localStorage.getItem('foo')) { 
      localStorage.setItem('foo', 'no reload') 
      location.reload() 
      window.scroll(0,0);
    } else {
      localStorage.removeItem('foo') 
    }
  }

  async getListNews(): Promise<void>{
    let result = await this.newsService.getListNews(null);
    this.listNews = result.data;
  }

  async getOtherConfig(): Promise<void>{
    let result = await this.otherConfigService.getOtherConfigPublic(null);
    this.otherConfig = result.data[0];
  }

  async getListintro(): Promise<void>{
    let result = await this.introService.getListintro(null);
    this.listIntro = result.data;    
  }

  closeForm(){
    this.close = 'hidden';
  }

  scroll() {
    let first = this.listIntro[0].description;
    let second = this.listIntro[1].description;
    let third = this.listIntro[2].description
    this.cAnimate("counter", 0, first, 50);
    this.cAnimate("counter2", 0, second, 150);
    this.cAnimate("counter3", 0, third, 100);
  };

  cAnimate(id, start, end, frameDelay = 100, mul = 1.2) {
    var obj = document.getElementById(id);
    var increment = 1;
    var current = start;
    var timer = setInterval(function() {
        current += increment;
        increment *= mul;
        if (current >= end) {
            current = end;
            clearInterval(timer);
        }
        obj.innerHTML = Math.floor(current).toLocaleString();
    }, frameDelay);
    
  }

  ngAfterViewInit(): void {
    $('.carousel.carousel-multi-item.v-2 .carousel-item').each(function(){
      var next = $(this).prev();
      if (!next.length) {
        next = $(this).siblings(':first');
      }
      next.children(':first-child').clone().appendTo($(this));
    
      for (var i=0;i<4;i++) {
        next=next.next();
        if (!next.length) {
          next=$(this).siblings(':first');
        }
        next.children(':first-child').clone().appendTo($(this));
      }
    });
  }
}
