import { BaseResponse } from './../../../shared/models/common-response.model';
import { HttpClient } from '@angular/common/http';
import { CommonResponseService } from './common-response.service';
import { Injectable } from '@angular/core';
import { Contact } from 'src/app/shared/models/contact.model';

@Injectable({
  providedIn: 'root'
})
export class ContactService extends CommonResponseService{
  constructor(protected http: HttpClient) {
    super(http);
  }

  async getContact(model:any): Promise<BaseResponse<any>>{
    return await super.getAsync('public/contact',model);
  }

  async updateContact(id : any, post: Contact): Promise<BaseResponse<any>> {
    return await super.putAsync(`admin/contact/${id}/edit`, post);  
  }
  
}
