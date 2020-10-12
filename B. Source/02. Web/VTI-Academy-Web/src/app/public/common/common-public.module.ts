import { ExperienceFormComponent } from './form-register/experience-form/experience-form.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormRegisterModule } from './form-register/form-register.module'
import { ScholarshipFormComponent } from './form-register/scholarship-form/scholarship-form.component';
import { MentorsSliderComponent } from './mentors-slider/mentors-slider.component';
import { EnterpriseComponent } from './enterprise/enterprise.component';
import { MainBannerComponent } from './main-banner/main-banner.component';
import { ImageIntroComponent } from './image-intro/image-intro.component';

@NgModule({
  declarations: [MentorsSliderComponent, EnterpriseComponent, MainBannerComponent, ImageIntroComponent],
  imports: [
    CommonModule,
    FormRegisterModule
    ],
  exports:[ScholarshipFormComponent, ExperienceFormComponent, MentorsSliderComponent, EnterpriseComponent, MainBannerComponent, ImageIntroComponent]
})
export class CommonPublicModule { }
