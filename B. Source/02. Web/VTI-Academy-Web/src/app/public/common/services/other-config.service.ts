import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseResponse } from 'src/app/shared/models/common-response.model';
import { OtherConfig } from 'src/app/shared/models/other-config.model';
import { CommonResponseService } from './common-response.service';

@Injectable({
  providedIn: 'root'
})
export class OtherConfigService extends CommonResponseService {

  constructor(protected http: HttpClient) {
    super(http);
   }

  async getOtherConfig(model:any){
    return await super.getAsync('admin/otherConfigs/getAll', model);
  }

  async editOtherConfig(formData : FormData) : Promise<BaseResponse<any>> {
    return await super.postAsync(`admin/otherConfigs/edit`, formData);
  }
  
  async editOtherConfigHaveImage(formData : FormData) : Promise<BaseResponse<any>> {
    return await super.postAsync(`admin/otherConfigs/edit/image`, formData);
  }

  async getOtherConfigPublic(model:any){
    return await super.getAsync('public/otherConfig', model);
  }
  
}
