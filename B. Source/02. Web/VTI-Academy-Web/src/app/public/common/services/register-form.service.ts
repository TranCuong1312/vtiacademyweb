import { BaseResponse } from './../../../shared/models/common-response.model';
import { CommonResponseService } from './common-response.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterFormService extends CommonResponseService{

  constructor(protected http: HttpClient) { 
    super(http);
  }

  async createScholarshipForm(post: any): Promise<BaseResponse<any>> {
    return await super.postAsync('public/register/1', post);
  }
  async createAdviceForm(post: any): Promise<BaseResponse<any>> {
    return await super.postAsync('public/register/2', post);
  }
  async createExperienceForm(post: any): Promise<BaseResponse<any>> {
    return await super.postAsync('public/register/3', post);
  }
  

}
