import { CommonPublicModule } from './../../common/common-public.module';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReviewRoutingModule } from './review-routing.module';
import { ReviewBusinessComponent } from './review-business/review-business.component';
import { ReviewStudentComponent } from './review-student/review-student.component';
import { ReviewMentorsComponent } from './review-mentors/review-mentors.component';
import { ReviewMentorsSliderComponent } from './review-mentors/review-mentors-slider/review-mentors-slider.component';

@NgModule({
  declarations: [ ReviewBusinessComponent, ReviewStudentComponent, ReviewMentorsComponent, ReviewMentorsSliderComponent],
  imports: [
    CommonModule,
    ReviewRoutingModule,
    CommonPublicModule
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class ReviewModule { }
