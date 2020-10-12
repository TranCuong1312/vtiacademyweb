import { CoursesComponent } from './courses/courses.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CoursedetailComponent } from './coursedetail/coursedetail.component';

const routes: Routes = [
  {
    path: '', component: CoursesComponent
  },
  {
    path: ':id',
    component: CoursedetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CourseRoutingModule { }
