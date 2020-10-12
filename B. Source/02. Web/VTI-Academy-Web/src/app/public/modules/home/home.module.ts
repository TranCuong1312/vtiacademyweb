import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { CommonPublicModule } from '../../common/common-public.module';
import { NewsModule } from '../news/news.module';
import { FormRegisterModule } from '../../common/form-register/form-register.module';


@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    HomeRoutingModule,
    CommonPublicModule,
    NewsModule ,
    FormRegisterModule
  ]
})
export class HomeModule { }
