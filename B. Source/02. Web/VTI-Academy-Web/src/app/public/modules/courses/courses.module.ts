import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CourseRoutingModule } from './courses-routing.module';
import { CoursesComponent } from './courses/courses.component';
import { ExperienceFormComponent } from '../../common/form-register/experience-form/experience-form.component';
import { CommonPublicModule } from '../../common/common-public.module';
import { CoursedetailComponent } from './coursedetail/coursedetail.component';

@NgModule({
  declarations: [CoursesComponent, CoursedetailComponent],
  imports: [
    CommonModule,
    CourseRoutingModule,
    CommonPublicModule,
 ],
  exports : []
})
export class CoursesModule { }
