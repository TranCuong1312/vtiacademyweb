import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PublicRoutingModule } from '../public/public-routing.module';

@NgModule({
  declarations: [PageNotFoundComponent,LoginComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports:[PageNotFoundComponent,LoginComponent]
})
export class SharedModule { }
