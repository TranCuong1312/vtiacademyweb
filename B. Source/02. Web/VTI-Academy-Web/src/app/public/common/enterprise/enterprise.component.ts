import { Component, OnInit } from '@angular/core';
import { Enterprise } from 'src/app/shared/models/enterprise.model';
import { EnterpriseService } from 'src/app/public/common/services/enterprise.service';
@Component({
  selector: 'app-enterprise',
  templateUrl: './enterprise.component.html',
  styleUrls: ['./enterprise.component.scss']
})
export class EnterpriseComponent implements OnInit {
  listEnterprise: Enterprise[] =[];

  constructor(private enterpriseService: EnterpriseService) { }

  ngOnInit(): void {
    this.getListEnterprise();
  }

  async getListEnterprise(): Promise<void>{
    let result = await this.enterpriseService.getListTop3Enterprise(null);
    this.listEnterprise= result.data;
  }

}
