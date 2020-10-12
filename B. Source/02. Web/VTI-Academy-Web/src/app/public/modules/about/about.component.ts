import { Intro } from './../../../shared/models/intro.model';
import { IntroService } from './../../common/services/intro.service';
import { AboutService } from './../../common/services/about.service';
import { About } from './../../../shared/models/about.model';
import { Component, OnInit, Sanitizer } from '@angular/core';
import { SafeResourceUrl, DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent implements OnInit {
  about: About;
  listIntro: Intro[] =[];
  video : SafeResourceUrl;

  constructor(private aboutService: AboutService, public sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.getAbout();
    if (!localStorage.getItem('foo')) { 
      localStorage.setItem('foo', 'no reload') 
      location.reload() 
      window.scroll(0,0);
    } else {
      localStorage.removeItem('foo') 
    }
  }

  async getAbout(): Promise<void>{
    let result = await this.aboutService.getAbout(null);
    this.about = result.data[0] ;
    this.video = this.sanitizer.bypassSecurityTrustResourceUrl(this.about.video);
  }

}
