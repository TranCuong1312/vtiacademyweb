import { Mentor } from './../../../shared/models/mentor.model';
import { PaginationResponse } from './../../../shared/models/pagination-response.model';
import { BaseResponse } from 'src/app/shared/models/common-response.model';
import { HttpClient } from '@angular/common/http';
import { CommonResponseService } from './common-response.service';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MentorsService extends CommonResponseService {
  constructor(protected http: HttpClient) {
    super(http);
  }

  async getListMentor(model: any): Promise<BaseResponse<any>> {
    return await super.getAsync('public/mentors', model);
  }

  async getListMentorReview(model: any): Promise<BaseResponse<any>> {
    return await super.getAsync('public/reviews/CHUYENGIA', model);
  }
  async getListMentorPaging(model: any,page: number): Promise<BaseResponse<PaginationResponse<Mentor[]>>> {
    return await super.getAsync(`admin/mentors?page=${page}&size=3`, model);
  }

  deleteMentor(id: number): Promise<any> {
    return this.http.delete(`${environment.endpointAPI}/admin/mentors/${id}`).toPromise();
  }

  addNewMentors(formData : FormData ): Observable<any>{
    return this.http.post(`${environment.endpointAPI}/admin/mentors/add`, formData, {responseType: 'text'});
  }

  editMentor(formData : FormData, id : String): Observable<any>{
    return this.http.post(`${environment.endpointAPI}/admin/mentors/edit/${id}`, formData, {responseType: 'text'});
  }

  editMentorHaveImage(formData : FormData, id : String): Observable<any>{
    return this.http.post(`${environment.endpointAPI}/admin/mentors/edit/image/${id}`, formData, {responseType: 'text'});
  }

}
