import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonResponseService } from './common-response.service';
import { BaseResponse } from 'src/app/shared/models/common-response.model';
import { PaginationResponse } from 'src/app/shared/models/pagination-response.model';
import { Enterprise } from './../../../shared/models/enterprise.model';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EnterpriseService extends CommonResponseService {
  constructor(protected http: HttpClient, protected toastr : ToastrService) {
    super(http);
  };

  async getListEnterprise(model: any): Promise<BaseResponse<any>> {
    return await super.getAsync('public/enterprise', model);
  }

  async getListEnterpriseAdmin(model: any): Promise<BaseResponse<any>> {
    return await super.getAsync('admin/enterprise', model);
  }

  async getListEnterprisePaging(model: any,page: number): Promise<BaseResponse<PaginationResponse<Enterprise[]>>> {
    return await super.getAsync(`admin/enterprise?page=${page}&size=3`, model);
  }

  async deleteEnterprise(id : number) :Promise<BaseResponse<any>>{
    return await super.deleteAsync(`admin/enterprise/${id}`);
  }

  async getListTop3Enterprise(model : any): Promise<BaseResponse<any>> {
    return await super.getAsync('admin/enterprise/top3', model);
  }

  addNewEnterprise(formData: FormData): Observable<any> {
    return this.http.post(environment.endpointAPI + '/admin/enterprise/add', formData, {responseType: 'text'});
  }

  editEnterprise(formData: FormData, id : string) : Observable<any> {
    return this.http.post(environment.endpointAPI + `/admin/enterprise/${id}/edit`, formData);
  }

  editIconEnterprise(formData: FormData, id : string) : Observable<any> {
    return this.http.post(environment.endpointAPI + `/admin/enterprise/${id}/icon/edit`, formData);
  }

  editImageEnterprise(formData: FormData, id : string) : Observable<any> {
    return this.http.post(environment.endpointAPI + `/admin/enterprise/${id}/image/edit`, formData);
  }

  editContentEnterprise(formData: FormData, id : string) : Observable<any> {
    return this.http.post(environment.endpointAPI + `/admin/enterprise/${id}/content/edit`, formData, {responseType: 'text'});
  }

}
