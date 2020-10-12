import { CourseService } from 'src/app/public/common/services/course.service';
import { Course } from 'src/app/shared/models/course.model';
import { SubCourse } from './../../../../shared/models/subcourse.model';
import { AuthService } from './../../../../shared/services/auth.service';
import { RegisterFormService } from './../../services/register-form.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-experience-form',
  templateUrl: './experience-form.component.html',
  styleUrls: ['./experience-form.component.scss']
})
export class ExperienceFormComponent implements OnInit {

  @Input('course') id: number;

  experienceForm: FormGroup;

  isSubmit: boolean = false;

  courseDetail: Course;

  constructor(private fb: FormBuilder, private registerFormService: RegisterFormService, private authService: AuthService, private courseService:CourseService) { }

  ngOnInit(): void {
    this.getCourseDetail();
    this.experienceForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(4)]],
      phone: ['', [Validators.required, Validators.pattern('[0-9]{10,11}')]],
      email: ['', [Validators.required, Validators.email]],
      course: ['']
    })
    
  }

  get name() { return this.experienceForm.get('name') }

  get phone() { return this.experienceForm.get('phone') }

  get email() { return this.experienceForm.get('email') }

  get course() { return this.experienceForm.get('course') }

  async getCourseDetail(): Promise<void> {
    let result = await this.courseService.getCourseDetail(null, this.id);
    this.courseDetail = result.data;
  }

  onSubmit() {
    this.course.setValue(this.courseDetail.name);
    if (this.experienceForm.invalid) {
      this.isSubmit = true;
      this.authService.showToastr(2,'Register fail' ,'');
    } else {
      this.registerFormService.createExperienceForm(this.experienceForm.value)
      .catch(
        error =>{
          this.authService.showToastr(2,'Register fail' ,'');
        }
      )
      .then(
        res =>{
          
        }
      );
      this.experienceForm.reset();
      this.authService.showToastr(1,'Congratulations, you have successfully registered!' ,'');
    }
  }
}