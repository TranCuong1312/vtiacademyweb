import { Component, OnInit } from '@angular/core';
import { Enterprise } from 'src/app/shared/models/enterprise.model';
import { EnterpriseService } from '../services/enterprise.service';

@Component({
  selector: 'app-image-intro',
  templateUrl: './image-intro.component.html',
  styleUrls: ['./image-intro.component.scss']
})
export class ImageIntroComponent implements OnInit {
  listEnterprise : Enterprise[] = [];

  constructor(private enterpriseService : EnterpriseService) { }

  ngOnInit(): void {
    this.getListEnterprise();
  }
  
  async getListEnterprise() {
    let result = await this.enterpriseService.getListEnterpriseAdmin(null);
    this.listEnterprise = result.data.contents;
    console.log(this.listEnterprise);
  }
} 
