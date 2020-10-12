import { ReactiveFormsModule } from '@angular/forms';
import { MentorsRoutingModule } from './mentors-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MentorComponent } from './mentor.component';

@NgModule({
  declarations: [MentorComponent],
  imports: [
    CommonModule,
    MentorsRoutingModule,
    ReactiveFormsModule
  ]
})
export class MentorModule { }
