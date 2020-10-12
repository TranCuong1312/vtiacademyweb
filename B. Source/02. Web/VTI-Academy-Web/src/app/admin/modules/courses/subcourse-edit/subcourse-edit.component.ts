import { AuthService } from './../../../../shared/services/auth.service';
import { CourseService } from 'src/app/public/common/services/course.service';
import { Course } from './../../../../shared/models/course.model';
import { Component, OnInit, Input } from '@angular/core';
import { SubCourse } from 'src/app/shared/models/subcourse.model';

@Component({
  selector: 'app-subcourse-edit',
  templateUrl: './subcourse-edit.component.html',
  styleUrls: ['./subcourse-edit.component.scss']
})
export class SubcourseEditComponent implements OnInit {

  @Input('course') course: Course;
  @Input('subCourse') subCourse: SubCourse;

  constructor(private courseService: CourseService, private authService: AuthService) { }

  ngOnInit(): void {
  }

  onDeleteClick(subCourse_id: number){
    this.deleteSubCourseInCourse(subCourse_id);
  }

  async deleteSubCourseInCourse(subCourse_id: number){
    await this.courseService.deleteSubCourseInCourse(this.course.id, subCourse_id)
    .then(
      (res) => {
        this.authService.showToastr(1, 'delete suceess', '');
      }
    )
    .catch(
      (error) => {
        this.authService.showToastr(2, 'delete fail', '');
    });
  }

}
