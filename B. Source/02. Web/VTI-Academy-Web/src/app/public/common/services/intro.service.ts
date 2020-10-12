import { BaseResponse } from './../../../shared/models/common-response.model';
import { CommonResponseService } from './common-response.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {IntroRequest} from '../../../admin/common/models/intro-request'
@Injectable({
  providedIn: 'root'
})
export class IntroService extends CommonResponseService{
  constructor(protected http: HttpClient) {
    super(http);
  }

  async getListintro(model:any): Promise<BaseResponse<any>>{
    return await super.getAsync('public/intro',model);
  }
  async updateIntro(id : number,intro : IntroRequest) : Promise<BaseResponse<any>> {
    return await super.putAsync(`admin/intro/${id}`,intro);
  }
}
