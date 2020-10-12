import { ReviewStudentComponent } from './review-student/review-student.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ReviewMentorComponent } from './review-mentor/review-mentor.component';

const routes: Routes = [
  { path: 'student',
    component : ReviewStudentComponent 
  },
  { path: 'mentor',
    component : ReviewMentorComponent 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReviewRoutingModule { }
