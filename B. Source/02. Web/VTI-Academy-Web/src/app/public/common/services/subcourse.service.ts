import { environment } from 'src/environments/environment';
import { SubCourse } from './../../../shared/models/subcourse.model';
import { PaginationResponse } from './../../../shared/models/pagination-response.model';
import { BaseResponse } from './../../../shared/models/common-response.model';
import { HttpClient } from '@angular/common/http';
import { CommonResponseService } from './common-response.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SubcourseService extends CommonResponseService {

  constructor(protected http: HttpClient) {
    super(http);
  }

  async getSubcoursesPaging(model:any, page: number): Promise<BaseResponse<PaginationResponse<SubCourse[]>>> {
    return await super.getAsync(`admin/Sub-Course?page=${page}&size=3`,model);
  }

  getAllSubcourses(): Promise<any> {
    return this.http.get(`${environment.endpointAPI}/admin/Sub-Course/getAll`).toPromise();
  }

  async addSubCourse(subCourse: SubCourse): Promise<BaseResponse<SubCourse>> {
    return await super.postAsync('admin/Sub-Course/add', subCourse);
  }

  async updatePost(id: number, subCourse: SubCourse): Promise<BaseResponse<SubCourse>> {
    return await super.putAsync(`admin/Sub-Course/update/${id}`,subCourse);
  }
  
  deletePost(id: number): Promise<any> {
    return this.http.delete(`${environment.endpointAPI}/admin/Sub-Course/delete/${id}`).toPromise();
  }

}
