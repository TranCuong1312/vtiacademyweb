import { Component, OnInit } from '@angular/core';
import { MainbannerService } from 'src/app/public/common/services/mainbanner.service';
import { MainBanner } from 'src/app/shared/models/mainbanner.model';
declare var $: any;

@Component({
  selector: 'app-main-banner',
  templateUrl: './main-banner.component.html',
  styleUrls: ['./main-banner.component.scss']
})
export class MainBannerComponent implements OnInit {

  banner : MainBanner[] = [];

  constructor(private mainBannerService: MainbannerService) { }

  ngOnInit(): void {
    this.getListBanner();
  }

  async getListBanner(): Promise<void>{
    let result = await this.mainBannerService.getAllBanner(null);
    this.banner = result.data;    
  }

  toNewTab(url : string) {
    window.open(url, "_blank");
  }
  
}
