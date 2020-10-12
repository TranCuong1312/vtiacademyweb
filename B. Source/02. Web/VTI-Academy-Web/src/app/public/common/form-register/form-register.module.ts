import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ScholarshipFormComponent } from './scholarship-form/scholarship-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ExperienceFormComponent } from './experience-form/experience-form.component';

@NgModule({
  declarations: [ScholarshipFormComponent, ExperienceFormComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [ScholarshipFormComponent, ExperienceFormComponent]
})
export class FormRegisterModule {}
