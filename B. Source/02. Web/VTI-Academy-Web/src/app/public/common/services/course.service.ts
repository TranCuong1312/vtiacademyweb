import { CourseOutcome } from './../../../shared/models/course-outcome.model';
import { environment } from 'src/environments/environment';
import { PaginationResponse } from './../../../shared/models/pagination-response.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BaseResponse } from 'src/app/shared/models/common-response.model';
import { CommonResponseService } from './common-response.service';
import { Course } from '../../../shared/models/course.model';

@Injectable({
  providedIn: 'root'
})
export class CourseService extends CommonResponseService {

  constructor(protected http: HttpClient) {
    super(http);
  }
  
  async getListCourses(model: Course): Promise<BaseResponse<any>> {
    return await super.getAsync('public/courses', model);
  }

  async createPost(post: Course): Promise<BaseResponse<any>> {
    return await super.postAsync('public/courses/create', post);
  }

  async editCourseHaveImage(id : number, course: FormData): Promise<BaseResponse<any>> {
    return await super.postAsync(`admin/courses/edit/${id}`, course);
  }

  async editCourseNoImage(id : number, course: FormData): Promise<BaseResponse<any>> {
    return await super.postAsync(`admin/courses/edit/${id}/noimage`, course);
  }

  async getCourseDetail(course: Course, id: number): Promise<BaseResponse<Course>> {
    let result = await super.getAsync(`public/courses/${id}`, course);
    return result;
  }

  async getListCoursesPaging(model: any, page: number): Promise<BaseResponse<PaginationResponse<Course[]>>> {
    return await super.getAsync(`admin/courses?page=${page}&size=3`, model);
  }

  getCoursesAdmin(id: number): Promise<any> {
    return this.http.get(`${environment.endpointAPI}/admin/courses/${id}`).toPromise();
  }

  getMaxId(){
    return this.http.get(`${environment.endpointAPI}/admin/courses/getMaxId`);
  }

  addCourses(formData: FormData){
    return this.http.post(`${environment.endpointAPI}/admin/courses/add`, formData);
  }

  addSubCoursesIntoCourse(courseID: number, subcourseID: number) {
    return this.http.post(`${environment.endpointAPI}/admin/courses/course/sub-courses/${courseID}/${subcourseID}`, null);
  }


  deletePost(id: number) {
    return this.http.delete(`${environment.endpointAPI}/admin/courses/${id}/delete`);
  }
  
  deleteCourseOutCome(course_id: number, courseOutcome_id: number): Promise<any> {
    return this.http.delete(`${environment.endpointAPI}/admin/courses/${course_id}/course-outcome/${courseOutcome_id}`).toPromise();
  }

  deleteSubCourseInCourse(course_id: number, subCourse_id: number) {
    return this.http.delete(`${environment.endpointAPI}/admin/courses/${course_id}/sub-courses/${subCourse_id}/delete`).toPromise();
  }

  deleteCourseSyllabuse(course_id : number) {
    return this.http.delete(`${environment.endpointAPI}/admin/courses/syllabuse/${course_id}/delete`).toPromise();
  }

  async createCourseOutcome(course_id: number, courseOutcome: CourseOutcome): Promise<BaseResponse<CourseOutcome>> {
    return await super.postAsync(`${environment.endpointAPI}/admin/courses/${course_id}/courseOutcome`, courseOutcome);
  }

}
