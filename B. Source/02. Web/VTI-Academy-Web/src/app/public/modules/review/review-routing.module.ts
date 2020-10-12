import { ReviewStudentComponent } from './review-student/review-student.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ReviewBusinessComponent } from './review-business/review-business.component';
import { ReviewMentorsComponent } from './review-mentors/review-mentors.component';

const routes: Routes = [
  {path: 'business', component: ReviewBusinessComponent},
  {path: 'student',component : ReviewStudentComponent },
  {path: 'mentor',component : ReviewMentorsComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReviewRoutingModule { }
