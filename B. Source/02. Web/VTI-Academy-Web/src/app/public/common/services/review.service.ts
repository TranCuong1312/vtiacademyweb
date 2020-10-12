import { Review } from './../../../shared/models/review.model';
import { PaginationResponse } from './../../../shared/models/pagination-response.model';
import { BaseResponse } from './../../../shared/models/common-response.model';
import { HttpClient } from '@angular/common/http';
import { CommonResponseService } from './common-response.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReviewService extends CommonResponseService{
  constructor(protected http: HttpClient) {
    super(http);
  }

  async getStudentReviews(model:any): Promise<BaseResponse<any>>{
    return await super.getAsync('public/reviews/HOCVIEN',model);
  }

  async getBussinessReviews(model:any): Promise<BaseResponse<any>>{
    return await super.getAsync('public/reviews/DOANHNGHIEP',model);
  }

  async getMentorReviews(model:any): Promise<BaseResponse<any>>{
    return await super.getAsync('public/reviews/CHUYENGIA',model);
  }

  async getStudentReviewPaging(model:any, page: number): Promise<BaseResponse<PaginationResponse<Review[]>>>{
    return await super.getAsync(`admin/reviews/type/HOCVIEN?page=${page}&size=3`, model);
  }

  async getBusinessReviewPaging(model:any, page: number): Promise<BaseResponse<PaginationResponse<Review[]>>>{
    return await super.getAsync(`admin/reviews/type/DOANHNGHIEP?page=${page}&size=3`, model);
  }

  async getMentorReviewPaging(model:any, page: number): Promise<BaseResponse<PaginationResponse<Review[]>>>{
    return await super.getAsync(`admin/reviews/type/CHUYENGIA?page=${page}&size=3`, model);
  }

  async deleteReview(id: number): Promise<BaseResponse<any>> {
    return await super.deleteAsync(`admin/reviews/delete/${id}`);
  }
  async addReview(formData : FormData): Promise<BaseResponse<any>>{
    return await super.postAsync(`admin/reviews/add`,formData)
  }

  async editReview(id:number, formData : FormData) : Promise<BaseResponse<any>> {
  return await super.postAsync(`admin/reviews/edit/${id}`, formData);
  }

  async editReviewHaveImage(id:number, formData : FormData) : Promise<BaseResponse<any>> {
    return await super.postAsync(`admin/reviews/edit/image/${id}`, formData);
    }
}
