import { BaseResponse } from './../../../shared/models/common-response.model';
import { HttpClient } from '@angular/common/http';
import { CommonResponseService } from './common-response.service';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import {AboutRequest} from '../../../admin/common/models/about-request'
@Injectable({
  providedIn: 'root'
})
export class AboutService extends CommonResponseService{
  constructor(protected http: HttpClient , protected toastr : ToastrService) {
    super(http);
  }

  async getAbout(model:any): Promise<BaseResponse<any>>{
    return await super.getAsync('public/aboutus',model);
  }

  async editAbout(id : number, about : AboutRequest) : Promise<BaseResponse<any>> {
    return await super.putAsync(`admin/aboutus/${id}`,about);
  }
}
