
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReviewRoutingModule } from './review-routing.module';
import { ReviewStudentComponent } from './review-student/review-student.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ReviewMentorComponent } from './review-mentor/review-mentor.component';


@NgModule({
  declarations: [ReviewStudentComponent, ReviewMentorComponent],
  imports: [
    CommonModule,
    ReviewRoutingModule,
    ReactiveFormsModule
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class ReviewModule { }
