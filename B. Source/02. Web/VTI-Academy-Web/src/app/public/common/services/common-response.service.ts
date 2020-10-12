import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {environment} from '../../../../environments/environment';
import {BaseResponse} from '../../../shared/models/common-response.model';

@Injectable({
  providedIn: 'root'
})
export class CommonResponseService {

  constructor(protected http: HttpClient) {
  }

  protected async getAsync(path, params?: object, defaultValue?: any): Promise<any> {
    try {
      const httpParams = CommonResponseService.generateParamsFrom(params);
      return await this.http.get<BaseResponse<any>>(`${environment.endpointAPI}/${path}`, {
        params: httpParams,
      }).toPromise();
    } catch (e) {
      console.error(e);
      return new BaseResponse({
        type : "ERROR",
        status: 2,
        message: e.message,
        data: defaultValue,
      })
    }
  }

  protected async postAsync(path, body?: object, defaultValue?: any): Promise<any> {
    try {
      return await this.http.post(`${environment.endpointAPI}/${path}`, body).toPromise()
    } catch (e) {
      console.error(e);
      return new BaseResponse({
        type : "ERROR",
        status: 2,
        message: e.message,
        data: defaultValue,
      })
    }
  }

  protected async putAsync(path, body?: object, defaultValue?: any): Promise<any> {
    try {
      return await this.http.put(`${environment.endpointAPI}/${path}`, body).toPromise()
    } catch (e) {
      console.error(e);
      return new BaseResponse({
        type : "ERROR",
        status: 2,
        message: e.message,
        data: defaultValue,
      })
    }
  }

  protected async deleteAsync(path, body?: object, defaultValue?: any): Promise<any> {
    try {
      return await this.http.delete(`${environment.endpointAPI}/${path}`, body).toPromise();
    } catch (e) {
      console.error(e);
      return new BaseResponse({
        type : "ERROR",
        status: 2,
        message: e.message,
        data: defaultValue,
      })
    }
  }

  private static generateParamsFrom(params?: Object): HttpParams {
    let httpParams = new HttpParams();
    for (const item in params) {
      if (params.hasOwnProperty(item) && params[item]) {
        httpParams = httpParams.set(item, params[item]);
      }
    }
    return httpParams;
  }
}
