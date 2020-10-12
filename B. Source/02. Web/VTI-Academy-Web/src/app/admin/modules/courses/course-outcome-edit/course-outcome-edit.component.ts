import { AuthService } from './../../../../shared/services/auth.service';
import { CourseService } from './../../../../public/common/services/course.service';
import { Course } from './../../../../shared/models/course.model';
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-course-outcome-edit',
  templateUrl: './course-outcome-edit.component.html',
  styleUrls: ['./course-outcome-edit.component.scss']
})
export class CourseOutcomeEditComponent implements OnInit {

  @Input('course') course: Course;
  courseOutcomeForm: FormGroup; 
  @Output() emitter: EventEmitter<any[]> = new EventEmitter();
  course2: Course;

  passDataToParent() {
    this.emitter.emit();
 }
  constructor(private courseService: CourseService, private authService: AuthService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.courseOutcomeForm = this.fb.group({
      name: ['']
    })
  }

  onDeleteClick(courseOutCome_Id: number) {
    this.deleteCourseOutcome(courseOutCome_Id);
  }

  async deleteCourseOutcome(courseOutCome_Id: number) {
    await this.courseService.deleteCourseOutCome(this.course.id, courseOutCome_Id)
      .then(
        res => {
          this.authService.showToastr(1, 'delete suceess', '');
          this.passDataToParent();
        }
      )
      .catch(
        error => {
          this.authService.showToastr(2, 'delete fail', '');
        })

  }

  get name() { return this.courseOutcomeForm.get('name') }

  onSubmit(){
    this.addCourseOutCome();
  }

  async addCourseOutCome(){
    await this.courseService.createCourseOutcome(this.course.id, this.courseOutcomeForm.value)
    .then(
      res => {
        this.authService.showToastr(1, 'Add suceess', '');
      }
    )
    .catch(
      error => {
        this.authService.showToastr(2, 'Add fail', '');
      });
  }

}
