import { PaginationResponse } from './../../../shared/models/pagination-response.model';
import { News } from '../../../shared/models/news.model';
import { BaseResponse } from 'src/app/shared/models/common-response.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CommonResponseService } from './common-response.service';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NewsService extends CommonResponseService{

  constructor(protected http: HttpClient,
    protected toastr : ToastrService ) {
    super(http);
  }
  async getListNews(model:any): Promise<BaseResponse<any>> {
    return await super.getAsync('public/news',model);
  }
  async getNewsDetail(news:News, id:number): Promise<BaseResponse<News>> {
    return await super.getAsync('public/news/'+ id,news);
  }
  async getNewsRandom(model:any): Promise<BaseResponse<any>> {
    return await super.getAsync('public/news-random',model);
  }

  async getListNews2(model:any): Promise<BaseResponse<any>> {
    return await super.getAsync('admin/news',model);
  }

  async getListNews2Paging(model:any, page: number): Promise<BaseResponse<PaginationResponse<News[]>>> {
    return await super.getAsync(`admin/news?page=${page}&size=3`,model);
  }

  addNews(formData : FormData ): Observable<any>{
    return this.http.post(environment.endpointAPI + "/admin/news", formData, {responseType: 'text'});
  }

  editNew(formData : FormData, id : String): Observable<any>{
    return this.http.post(environment.endpointAPI + `/admin/news/edit/noimage/${id}`, formData, {responseType: 'text'});
  }

  editNewHaveImage(formData : FormData, id : String): Observable<any>{
    return this.http.post(environment.endpointAPI + `/admin/news/edit/${id}`, formData, {responseType: 'text'});
  }

  async deleteNews(id : number) :Promise<BaseResponse<any>>{
    return await super.deleteAsync(`admin/news/delete/${id}`);
  }

}