import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseResponse } from 'src/app/shared/models/common-response.model';
import { CommonResponseService } from './common-response.service';
import { MainBanner } from './../../../shared/models/mainbanner.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MainbannerService extends CommonResponseService{

  constructor(protected http: HttpClient) {
    super(http);
  }

  async getListBanner(model: MainBanner): Promise<BaseResponse<any>> {
    return await super.getAsync('admin/mainbanner', model);
  }

  async getAllBanner(model: MainBanner): Promise<BaseResponse<any>> {
    return await super.getAsync('admin/mainbanner/allbanner', model);
  }

  async getPagingBanner(model: MainBanner, page: Number): Promise<BaseResponse<any>>{
    return await super.getAsync(`admin/mainbanner/paging?page=${page}&size=3`, model);
  }

  addBanner(formData : FormData ): Observable<any>{
    return this.http.post( environment.endpointAPI + "/admin/mainbanner/add", formData, {responseType: 'text'});
  }

  editBanner(formData : FormData, id : String): Observable<any>{
    return this.http.post(environment.endpointAPI + `/admin/mainbanner/editcontent/${id}`, formData, {responseType: 'text'});
  }

  editBannerHaveImage(formData : FormData, id : String): Observable<any>{
    return this.http.post(environment.endpointAPI + `/admin/mainbanner/edit/${id}`, formData, {responseType: 'text'});
  }

  deleteBanner(id : Number) : Observable<any>{
    return this.http.delete(environment.endpointAPI + `/admin/mainbanner/delete/${id}`);
  }
}
